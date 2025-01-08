import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart';

class OtherLogin extends StatefulWidget {
  const OtherLogin({super.key});

  @override
  State<OtherLogin> createState() => _OtherLoginState();
}

class _OtherLoginState extends State<OtherLogin> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
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
        const SizedBox(
          height: 10,
        ),
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
            const SizedBox(width: 20),
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
            const SizedBox(width: 20),
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
      ],
    );
  }
}
