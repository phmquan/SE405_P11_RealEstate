import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart';

class CustomTextButton extends StatelessWidget {
  final VoidCallback onTap;
  final String? btnText;
  final Color? btnTextColor;
  final double? fontSize;
  final String? imagePath;  // Path to the image
  final double? imageSize;  // Size of the image


  const CustomTextButton({
    super.key,
    required this.onTap,
    this.btnText,
    this.btnTextColor = AppColors.blue,
    this.fontSize = 12,
    this.imagePath,
    this.imageSize = 40
  });

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: onTap,
      child: Container(
        padding: EdgeInsets.symmetric(horizontal: 5, vertical: 5),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(8),
          color: Colors.transparent, // You can change it to any color you want
        ),
        child: Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              if (imagePath != null)
                Image.asset(
                  imagePath!,
                  width: imageSize ?? 24, // Default size if not provided
                  height: imageSize ?? 24,
                ),
              if (btnText != null)
                Text(
                  btnText!,
                  style: TextStyle(
                    fontSize: fontSize,
                    fontWeight: FontWeight.bold,
                    color: btnTextColor,
                  ),
                ),
            ],
          ),
        ),
      ),
    );
  }
}
