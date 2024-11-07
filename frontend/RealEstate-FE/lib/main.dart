import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart';
import 'package:frontend/pages/screens/login_screen.dart';
import 'package:frontend/pages/screens/register_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        
        colorScheme: ColorScheme.fromSeed(
          primary: AppColors.blue,
          seedColor: AppColors.lightBlue
        ),
        scaffoldBackgroundColor: AppColors.white,

        useMaterial3: true,
      ),
      home: 
      //LoginScreen(),
      RegisterScreen(),
    );
  }
}
