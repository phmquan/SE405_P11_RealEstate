import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/pages/routes/routes.dart';

void main() {
  runApp(
    const ProviderScope(
      child: MyApp(),
    ),
  );
}

class MyApp extends ConsumerWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final goRouter = ref.watch(goRouterProvider);
    
    return MaterialApp.router(
      debugShowCheckedModeBanner: false,
      routerConfig: goRouter,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(
          primary: AppColors.blue,
          seedColor: AppColors.lightBlue
        ),
        scaffoldBackgroundColor: AppColors.white,
        useMaterial3: true,
      ),
    );
  }
}