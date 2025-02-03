// ignore_for_file: avoid_print

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/pages/screens/Authentication/forgot_password.dart';
import 'package:frontend/pages/screens/Authentication/login.dart';
import 'package:frontend/pages/screens/Authentication/register.dart';
import 'package:frontend/pages/screens/Chat/chat.dart';
import 'package:frontend/pages/screens/Dashboard/dashboard.dart';
import 'package:frontend/pages/screens/Home/home.dart';
import 'package:frontend/pages/screens/Posting/posting.dart';
import 'package:frontend/pages/screens/Setting/setting.dart';
import 'package:frontend/widgets/bottom_navigation.dart';
import 'package:go_router/go_router.dart';

class RoutePaths {
  static const String home = '/home';
  static const String dashboard = '/dashboard';
  static const String posting = '/posting';
  static const String notification = '/notification';
  static const String chat = '/chat';
  static const String setting = '/setting';
  static const String editProfile = '/edit_profile';
  static const String login = '/login';
  static const String register = '/register';
  // static const String confirm = '/confirm';
  static const String forgotPassword = '/forgot_password';
  static const String changePassword = '/change_password';
  //Logged in user
  static const String confirmLogged = '/confirm';
  static const String forgotPasswordLogged = '/forgot_password';
  static const String changePasswordLogged = '/change_password';
}

final goRouterProvider = Provider<GoRouter>((ref) {
  final rootNavKey = GlobalKey<NavigatorState>(debugLabel: 'rootNav');

  return GoRouter(
    initialLocation: RoutePaths.home,
    navigatorKey: rootNavKey,
    routes: [
      StatefulShellRoute.indexedStack(
        builder: (context, state, navigationShell) =>
            ScaffoldWithNavBar(navigationShell: navigationShell),
        branches: [
          StatefulShellBranch(
            routes: [
              GoRoute(
                path: RoutePaths.home,
                builder: (context, state) => const Home(),
              ),
            ],
          ),
          StatefulShellBranch(
            routes: [
              GoRoute(
                path: RoutePaths.dashboard,
                builder: (context, state) => const Dashboard(),
              ),
            ],
          ),
          StatefulShellBranch(
            routes: [
              GoRoute(
                path: RoutePaths.posting,
                builder: (context, state) => const Posting(),
              ),
            ],
          ),
          StatefulShellBranch(
            routes: [
              GoRoute(
                path: RoutePaths.chat,
                builder: (context, state) => const Chat(),
              ),
            ],
          ),
          StatefulShellBranch(
            routes: [
              GoRoute(
                path: RoutePaths.setting,
                builder: (context, state) => const Setting(),
              ),
            ],
          ),
        ],
      ),
      GoRoute(
        path: RoutePaths.login,
        builder: (context, state) => const Login(),
      ),
      GoRoute(
        path: RoutePaths.register,
        builder: (context, state) => const Register(),
      ),
      GoRoute(
        path: RoutePaths.forgotPassword,
        builder: (context, state) => const ForgotPassword(),
      ),
      GoRoute(
        path: RoutePaths.changePassword,
        builder: (context, state) => const ForgotPassword(),
      ),
    ],
  );
});
