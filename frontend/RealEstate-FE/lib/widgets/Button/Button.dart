import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart';

class CustomButton extends StatelessWidget {
  final VoidCallback onTap;
  final double? btnWidth;
  final double? btnHeight;
  final String? btnText;
  final Color? btnTextColor;
  final double? fontSize;
  final Color? btnColor;
  final IconData? icon;
  final TextAlign? textAlign;
  final double? borderRadius;
  final double? iconSize;
  final Color? iconColor;
  final Color? borderColor;

  const CustomButton({
    super.key,
    required this.onTap,
    this.btnText,
    this.btnTextColor = AppColors.white,
    this.fontSize = 12,
    this.btnWidth,
    this.btnHeight = 30,
    this.btnColor = AppColors.blue,
    this.icon,
    this.iconSize,
    this.iconColor,
    this.textAlign,
    this.borderRadius = 10,
    this.borderColor = AppColors.white,
  });

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: onTap,
      style: ElevatedButton.styleFrom(
        minimumSize: Size(btnWidth ?? MediaQuery.of(context).size.width * 0.06,
            btnHeight ?? MediaQuery.of(context).size.height * 0.06),
        backgroundColor: btnColor,
        elevation: 0.0,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(borderRadius!),
          side: BorderSide(
            color: borderColor ?? AppColors.white,   
          ),
        ),
      ),
      // child: Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.start,
          mainAxisSize: MainAxisSize.min,
        
          children: [
            if (icon != null) Icon(icon, color: iconColor, size: iconSize,),
            if (icon != null && btnText != null) SizedBox(width: 8),
            if (btnText != null)
              Text(
                btnText!,
                style: TextStyle(
                  fontSize: fontSize,
                  color: btnTextColor,
                ),
              ),
          ],
        // ),
      ),
    );
  }
}
