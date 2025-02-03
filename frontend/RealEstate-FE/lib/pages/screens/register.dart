import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart';
import 'package:frontend/assets/icons/icons.dart';
import 'package:frontend/pages/screens/login.dart';
import 'package:frontend/widgets/other_login.dart';

class Register extends StatefulWidget {
  const Register({super.key});

  @override
  State<Register> createState() => RegisterState();
}

class RegisterState extends State<Register> {
  final registerfield = GlobalKey<FormState>();
  final emailController = TextEditingController();
  final numberController = TextEditingController();
  final passwordController = TextEditingController();

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
              key: registerfield, // key
              child: Column(
                children: [
                  const Text(
                    "ĐĂNG KÝ",
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
                    keyboardType: TextInputType.number,
                    controller: numberController,
                    decoration: InputDecoration(
                      labelText: "Số điện thoại",
                      border: const OutlineInputBorder(),
                      prefixIcon: AppIcons.getIcon(AppIcons.phone),
                      contentPadding: const EdgeInsets.symmetric(
                          vertical: 10.0, horizontal: 10),
                    ),
                    validator: (value) {
                      bool emailValid = RegExp(r'^0[0-9]{9}$').hasMatch(value!);

                      if (value.isEmpty) {
                        return "Nhập số điện thoại";
                      } else if (!emailValid) {
                        return "Số điện thoại không hợp lệ";
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
                            : AppIcons.visibility_off),
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
                      if (registerfield.currentState!.validate()) {
                        emailController.clear();
                        passwordController.clear();
                      }
                      //Handle đăng nhập
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
                  const SizedBox(
                    height: 10,
                  ),
                  const OtherLogin(),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const Text(
                        "Đã có tài khoản?",
                        style: TextStyle(
                          fontSize: 14,
                        ),
                      ),
                      TextButton(
                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => const Login()),
                          );
                        },
                        child: const Text(
                          "Đăng nhập",
                          style: TextStyle(
                            fontSize: 14,
                            color: AppColors.blue,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              )),
        ),
      ),
    );
  }
}
