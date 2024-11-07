import 'package:flutter/material.dart';
import '../../../assets/colors/colors.dart';
import '../../../assets/icons/icons.dart';


class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State <LoginScreen> createState() =>  LoginScreenState();
}

class LoginScreenState extends State <LoginScreen> {
  final loginfield = GlobalKey<FormState>();
  final emailController = TextEditingController();
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
            key: loginfield, // key
            child: Column(
              children: [
                const Text(
                  "ĐĂNG NHẬP",
                  style: TextStyle(
                    color: AppColors.blue,
                    fontSize: 24,
                    fontWeight: FontWeight.bold
                  ),
                ),
                const SizedBox(height: 30,),
                TextFormField(
                  keyboardType: TextInputType.emailAddress,
                  controller: emailController,
                  decoration: InputDecoration(
                    labelText: "Email",
                    border: OutlineInputBorder(),
                    prefixIcon: AppIcons.getIcon(AppIcons.email),
                    contentPadding: const EdgeInsets.symmetric(
                      vertical: 10.0, 
                      horizontal: 10
                    ),
                  ),
                  validator: (value) {
                    bool emailValid = RegExp(
                          r"^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9]+\.[a-zA-Z]+")
                        .hasMatch(value!);

                    if(value.isEmpty) {
                      return "Nhập email";
                    } else if(!emailValid) {
                      return "Email không hợp lệ";
                    }
                    return null;
                  },
                ),
                const SizedBox(height: 20,),
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
                      child: AppIcons.getIcon(passToggle ? AppIcons.visibility : AppIcons.visibility_off),
                    ),
                    contentPadding: const EdgeInsets.symmetric(
                      vertical: 10.0, 
                      horizontal: 10
                    ),
                  ),
                  validator: (value) {
                    if(value!.isEmpty) {
                      return "Nhập mật khẩu";
                    } else if (passwordController.text.length < 6) {
                      return "Độ dài mật khẩu không dưới 6 kí tự";
                    }
                    return null;
                  },
                ),
                const SizedBox(height: 20,),
                InkWell(
                  onTap: () {
                    if(loginfield.currentState!.validate()) {
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
                        //mở REGISTER SCREEN
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
                const SizedBox(height: 10,),
                const Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  children: [
                    Divider(
                      color: Colors.black,
                      height: 1,
                    ),
                    Text(
                      "Hoặc đăng nhập bằng"
                    ),
                    Divider(
                      color: Colors.black,
                      height: 1,
                    ),
                  ],
                ),
                const SizedBox(height: 10,),
                TextButton(
                  onPressed: () {
                    //mở FORGOT PASSWORD SCREEN
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
            )
          ),
        ),
        
      ),
    );
  }
}