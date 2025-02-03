// import 'package:dio/dio.dart';
// final dio = Dio();

// void request() async {
//   Response response;
//   response = await dio.post("http://localhost:8080/api/v1/auth/login",data: {'username':'username@gmail.com','password':'123456'});
//   print(response.data.toString());
// }

// dio.interceptors
//     .add(InterceptorsWrapper(onRequest: (options, handler) async {
// if (!options.path.contains('http')) {
// // Cấu hình đường path để call api, thành phần gồm
// // - Enviroment.api: Enpoint api theo môi trường, có thể dùng package dotenv
// // để cấu hình biến môi trường. Ví dụ: https://api-tech.com/v1
// // - options.path: đường dẫn cụ thể API. Ví dụ: "user/user-info"

// options.path = Enviroment.apiUrl + options.path;
// }
// // Đoạn này dùng để config timeout api từ phía client, tránh việc call 1 API
// // bị lỗi trả response quá lâu.
// options.connectTimeout = 3000;
// options.receiveTimeout = 3000;
// // Gắn access_token vào header, gửi kèm access_token trong header mỗi khi call API
// options.headers['Authorization'] = "Bearer $accessToken";
// }, onResponse: (Response response, handler) {
// // Do something with response data
// return handler.next(response);
// }, onError: (DioError error, handler) async {
// return handler.next(error);
// }));
