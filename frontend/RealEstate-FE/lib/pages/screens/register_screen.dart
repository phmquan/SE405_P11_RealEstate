import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart';
import 'package:frontend/assets/icons/icons.dart';


class RegisterScreen extends StatefulWidget {
  const RegisterScreen({super.key});

  @override
  State <RegisterScreen> createState() =>  RegisterScreenState();
}

class RegisterScreenState extends State <RegisterScreen> {
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
            vertical: 10, 
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
                    fontWeight: FontWeight.bold
                  ),
                ),
                const SizedBox(height: 30,),
                TextFormField(
                  keyboardType: TextInputType.emailAddress,
                  controller: emailController,
                  decoration: InputDecoration(
                    labelText: "Email",
                    border: const OutlineInputBorder(),
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
                  keyboardType: TextInputType.number,
                  controller: numberController,
                  decoration: InputDecoration(
                    labelText: "Số điện thoại",
                    border: const OutlineInputBorder(),
                    prefixIcon: AppIcons.getIcon(AppIcons.phone),
                    contentPadding: const EdgeInsets.symmetric(
                      vertical: 10.0, 
                      horizontal: 10
                    ),
                  ),
                  validator: (value) {
                    bool emailValid = RegExp(
                          r'^0[0-9]{9}$')
                        .hasMatch(value!);

                    if(value.isEmpty) {
                      return "Nhập số điện thoại";
                    } else if(!emailValid) {
                      return "Số điện thoại không hợp lệ";
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
                    if(registerfield.currentState!.validate()) {
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
                const SizedBox(height: 10,),
                const Row(
                  children: [
                    Expanded(
                      child: Divider(
                        color: AppColors.gray,
                        height: 1,
                      ),
                    ),
                    Text(
                      " Hoặc đăng nhập bằng ",
                      style: TextStyle(
                        fontSize: 12, 
                        
                      ),
                    ),
                    Expanded(
                      child: Divider(
                        color: AppColors.gray,
                        height: 1,
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 10,),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Expanded(
                  child: ElevatedButton(
                    onPressed: () {
                      // handle Facebook
                    },
                    style: ElevatedButton.styleFrom(
                      side: const BorderSide(color: Colors.grey),
                      padding: const EdgeInsets.symmetric(vertical: 15),
                      elevation: 0,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                    ),
                    child: Center(
                      child: Image.asset(
                        'asset/fb.png',
                        width: 25,
                        height: 25,
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
                ),
                const SizedBox(width: 20), // khoảng cách giữa các button
                Expanded(
                  child: ElevatedButton(
                    onPressed: () {
                      // handle Google
                    },
                    style: ElevatedButton.styleFrom(
                      side: const BorderSide(color: Colors.grey),
                      padding: const EdgeInsets.symmetric(vertical: 15),
                      elevation: 0,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                    ),
                    child: Center(
                      child: Image.asset(
                        'asset/google.png',
                        width: 25,
                        height: 25,
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
                ),
                const SizedBox(width: 20), // khoảng cách giữa các button
                Expanded(
                  child: ElevatedButton(
                    onPressed: () {
                      // handle Zalo
                    },
                    style: ElevatedButton.styleFrom(
                      side: const BorderSide(color: Colors.grey),
                      padding: const EdgeInsets.symmetric(vertical: 15),
                      elevation: 0,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                    ),
                    child: Center(
                      child: Image.asset(
                        'asset/zalo.jpg',
                        width: 25,
                        height: 25,
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
                ),
              ],
            ),
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
                        //mở REGISTER SCREEN
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
            )
          ),
        ),
        
      ),
    );
  }
}