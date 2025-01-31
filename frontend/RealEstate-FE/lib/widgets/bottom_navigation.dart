
import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart';
import 'package:frontend/assets/icons/icons.dart';
import 'package:go_router/go_router.dart';


class ScaffoldWithNavBar extends StatelessWidget {
  const ScaffoldWithNavBar({
    required this.navigationShell,
    Key? key,
  }) : super(key: key ?? const ValueKey<String>('ScaffoldWithNavBar'));

  final StatefulNavigationShell navigationShell;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: navigationShell,
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(AppIcons.home, size: 30.0),
            label: 'Trang chủ',
          ),
          BottomNavigationBarItem(
            icon: Icon(AppIcons.list, size: 30.0),
            label: 'Quản lý tin',
          ),
          BottomNavigationBarItem(
            icon: Icon(AppIcons.edit, size: 30.0),
            label: 'Đăng tin',
          ),
          BottomNavigationBarItem(
            icon: Icon(AppIcons.chat, size: 30.0),
            label: 'Tin nhắn',
          ),
          BottomNavigationBarItem(
            icon: Icon(AppIcons.person, size: 30.0),
            label: 'Tài khoản',
          ),
        ],
        currentIndex: navigationShell.currentIndex,
        onTap: (int index) => _onTap(context, index),
        selectedItemColor: AppColors.blue,
        unselectedItemColor: AppColors.gray,
        showUnselectedLabels: true,
      ),
    );
  }

  void _onTap(BuildContext context, int index) {
    navigationShell.goBranch(
      index,
      initialLocation: index == navigationShell.currentIndex,
    );
  }
}

