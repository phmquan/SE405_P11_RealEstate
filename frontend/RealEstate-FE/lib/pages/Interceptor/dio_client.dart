import 'package:dio/dio.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:frontend/pages/Interceptor/interceptor.dart';

class DioClient {
  final Dio _dio = Dio();
  final FlutterSecureStorage _secureStorage = const FlutterSecureStorage();

  DioClient() {
    _dio.options.baseUrl = 'http://10.0.2.2:8080';
    _dio.interceptors.add(AuthInterceptor(
      dio: _dio,
      secureStorage: _secureStorage,
    ));
  }

  Dio get dio => _dio;
  FlutterSecureStorage get secureStorage => _secureStorage;
}
