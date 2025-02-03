import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/assets/colors/colors.dart';

class Setting extends ConsumerStatefulWidget {
  const Setting({super.key});

  @override
  SettingState createState() => SettingState();
}

class SettingState extends ConsumerState<Setting> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "Tài khoản",
          style: TextStyle(
            color: AppColors.white,
            fontSize: 28,
            fontWeight: FontWeight.normal
          ),
        ),
        centerTitle: true,
        backgroundColor: AppColors.blue,
      ),
      body: ListView(
        padding: const EdgeInsets.all(16.0),
        children: [
          // Đăng nhập/Đăng ký
          ListTile(
            leading: const CircleAvatar(
              backgroundColor: Colors.blue, 
              child: Icon(
                Icons.people, 
                color: Colors.white, 
                size: 30, 
              ),
            ),
            title: const Text(
              'Đăng nhập/Đăng ký',
              style: TextStyle(
                fontSize: 12, 
                fontWeight: FontWeight.bold, 
                color: Colors.black, 
              ),
            ),
            onTap: () {},
          ),
          const Divider(),

          // Lịch hẹn
          ListTile(
            leading: const CircleAvatar(
              backgroundColor: Colors.blue, 
              child: Icon(
                Icons.calendar_today, 
                color: Colors.white, 
                size: 20, 
              ),
            ),
            title: const Text(
              'Lịch hẹn',
              style: TextStyle(
                fontSize: 12, 
                fontWeight: FontWeight.normal, 
                color: Colors.black,
              ),
            ),
            onTap: () {},
          ),
          const Divider(),

          // Cài đặt tài khoản
          ListTile(
            leading: const CircleAvatar(
              backgroundColor: Colors.blue, 
              child: Icon(
                Icons.settings, 
                color: Colors.white, 
                size: 20, 
              ),
            ),
            title: const Text(
              'Cài đặt tài khoản',
              style: TextStyle(
                fontSize: 12, 
                fontWeight: FontWeight.normal, 
                color: Colors.black, 
              ),
            ),
            onTap: () {},
          ),
          const Divider(),

          // Trợ giúp
          ListTile(
            leading: const CircleAvatar(
              backgroundColor: Colors.blue, 
              child: Icon(
                Icons.help, 
                color: Colors.white, 
                size: 20, 
              ),
            ),
            title: const Text(
              'Trợ giúp',
              style: TextStyle(
                fontSize: 12, 
                fontWeight: FontWeight.normal, 
                color: Colors.black, 
              ),
            ),
            onTap: () {},
          ),
          const Divider(),

          // Đóng góp ý kiến
          ListTile(
            leading: const CircleAvatar(
              backgroundColor: Colors.blue, 
              child: Icon(
                Icons.feedback, 
                color: Colors.white, 
                size: 20, 
              ),
            ),
            title: const Text(
              'Đóng góp ý kiến',
              style: TextStyle(
                fontSize: 12, 
                fontWeight: FontWeight.normal, 
                color: Colors.black, 
              ),
            ),
            onTap: () {},
          ),
        ],
      ),
    );
  }
}