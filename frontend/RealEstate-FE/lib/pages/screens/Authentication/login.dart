import 'package:flutter/material.dart';
import 'package:frontend/pages/routes/routes.dart';
import 'package:frontend/pages/screens/Authentication/forgot_password.dart';
import 'package:frontend/pages/screens/Authentication/register.dart';
import 'package:frontend/pages/screens/Home/home.dart';
import 'package:frontend/widgets/other_login.dart';
import 'package:go_router/go_router.dart';
import '../../../../assets/colors/colors.dart';
import '../../../../assets/icons/icons.dart';
import 'package:dio/dio.dart';
class Login extends StatefulWidget {
  const Login({super.key});

  @override
  State<Login> createState() => LoginState();
}

class LoginState extends State<Login> {
  final loginfield = GlobalKey<FormState>();
  final emailController = TextEditingController();
  final passwordController = TextEditingController();
  final dio = Dio();
  void request() async {
    Response response;
    response = await dio.post("http://localhost:8080/api/v1/auth/login",data: {'username':'username@gmail.com','password':'123456'});
    print(response.data.toString());
  }
  bool passToggle = true;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("logo app"),
        centerTitle: true,
        backgroundColor: AppColors.lightBlue,
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.symmetric(
            horizontal: 20,
            vertical: 20,
          ),
          child: Form(
              key: loginfield, // key
              child: Column(
                children: [
                  const Text(
                    "ĐĂNG NHẬP",
                    style: TextStyle(
                        color: AppColors.blue,
                        fontSize: 24,
                        fontWeight: FontWeight.bold),
                  ),
                  const SizedBox(
                    height: 30,
                  ),
                  TextFormField(
                    keyboardType: TextInputType.emailAddress,
                    controller: emailController,
                    decoration: InputDecoration(
                      labelText: "Email",
                      border: const OutlineInputBorder(),
                      prefixIcon: AppIcons.getIcon(AppIcons.email),
                      contentPadding: const EdgeInsets.symmetric(
                          vertical: 10.0, horizontal: 10),
                    ),
                    validator: (value) {
                      bool emailValid = RegExp(
                              r"^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9]+\.[a-zA-Z]+")
                          .hasMatch(value!);

                      if (value.isEmpty) {
                        return "Nhập email";
                      } else if (!emailValid) {
                        return "Email không hợp lệ";
                      }
                      return null;
                    },
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  TextFormField(
                    keyboardType: TextInputType.emailAddress,
                    controller: passwordController,
                    obscureText: passToggle,
                    decoration: InputDecoration(
                      labelText: "Mật khẩu",
                      border: const OutlineInputBorder(
                          //borderSide: BorderSide(color: Colors.blue),
                          ),
                      prefixIcon: AppIcons.getIcon(AppIcons.lock),
                      suffixIcon: InkWell(
                        onTap: () {
                          setState(() {
                            passToggle = !passToggle;
                          });
                        },
                        child: AppIcons.getIcon(passToggle
                            ? AppIcons.visibility
                            : AppIcons.visibilityOff),
                      ),
                      contentPadding: const EdgeInsets.symmetric(
                          vertical: 10.0, horizontal: 10),
                    ),
                    validator: (value) {
                      if (value!.isEmpty) {
                        return "Nhập mật khẩu";
                      } else if (passwordController.text.length < 6) {
                        return "Độ dài mật khẩu không dưới 6 kí tự";
                      }
                      return null;
                    },
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  InkWell(
                    onTap: () {
                      // if (loginfield.currentState!.validate()) {
                      //   emailController.clear();
                      //   passwordController.clear();
                      //   request();
                      // }
                      context.go(RoutePaths.home);

                    },
                    child: Container(
                      height: 50,
                      decoration: BoxDecoration(
                        color: AppColors.blue,
                        borderRadius: BorderRadius.circular(5),
                      ),
                      child: const Center(
                        child: Text(
                          "Đăng nhập",
                          style: TextStyle(
                            color: AppColors.white,
                            fontSize: 16,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                    ),
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const Text(
                        "Chưa có tài khoản?",
                        style: TextStyle(
                          fontSize: 16,
                        ),
                      ),
                      TextButton(
                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(builder: (context) => Register()),
                          );
                        },
                        child: const Text(
                          "Đăng ký tài khoản mới",
                          style: TextStyle(
                            fontSize: 16,
                            color: AppColors.blue,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(
                    height: 10,
                  ),
                  const OtherLogin(),
                  const SizedBox(
                    height: 10,
                  ),
                  TextButton(
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => ForgotPassword()),
                      );
                    },
                    child: const Text(
                      "Quên mật khẩu",
                      style: TextStyle(
                        fontSize: 16,
                        color: AppColors.blue,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                ],
              )),
        ),
      ),
    );
  }
}
