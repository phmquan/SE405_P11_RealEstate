import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart';
import 'package:frontend/assets/icons/icons.dart';

class CustomTextField extends StatefulWidget {
  final String? hintText;
  final IconData? icon;
  final TextEditingController controller;
  final bool isLeftIcon;
  final Color iconColor;
  final Color backgroundColor;
  final Color hintTextColor;
  final bool isPassword;
  final double textFieldWidth;
  final double textFieldHeight;
  final List<String>? autofillHints;

  const CustomTextField({
    super.key,
    this.hintText,
    this.icon,
    required this.controller,
    this.isLeftIcon = true,
    this.iconColor = AppColors.gray,
    this.backgroundColor = AppColors.lightGrey,
    this.hintTextColor = AppColors.lightGrey,
    this.isPassword = false,
    this.textFieldWidth = double.infinity,
    this.textFieldHeight = 50,
    this.autofillHints,
  });

  @override
  State <CustomTextField> createState() => _CustomTextFieldState();
}

class _CustomTextFieldState extends State<CustomTextField> {
  bool _obscureText = true;

  @override
  Widget build(BuildContext context) {
    return Container(
      width: widget.textFieldWidth,
      height: widget.textFieldHeight,
      padding: const EdgeInsets.symmetric(horizontal: 15),
      decoration: BoxDecoration(
        color: widget.backgroundColor,
        borderRadius: BorderRadius.circular(10),
      ),
      child: Row(
        children: [
          if (widget.isLeftIcon)
            Padding(
              padding: const EdgeInsets.only(right: 15),
              child: Icon(widget.icon, color: widget.iconColor),
            ),
          Expanded(
            child: TextField(
              enableSuggestions: false,
              autocorrect: false,
              controller: widget.controller,
              style: const TextStyle(color: Colors.black),
              obscureText: widget.isPassword ? _obscureText : false,
              decoration: InputDecoration(
                hintText: widget.hintText,
                hintStyle: TextStyle(
                    color: widget.hintTextColor,
                    fontSize: 14,
                    fontFamily: 'Mulish'),
                border: InputBorder.none,
                suffixIcon: widget.isPassword
                    ? IconButton(
                        icon: Icon(
                          _obscureText
                              ? AppIcons.visibilityOff
                              : AppIcons.visibility,
                          color: AppColors.gray,
                        ),
                        onPressed: () {
                          setState(() {
                            _obscureText = !_obscureText;
                          });
                        },
                      )
                    : null,

                // enabledBorder: OutlineInputBorder(
                //   borderRadius: BorderRadius.circular(100),
                //   borderSide: BorderSide.none,
                // ),
                // focusedBorder: OutlineInputBorder(
                //   borderRadius: BorderRadius.circular(100),
                //   borderSide: BorderSide.none,
                // ),
              ),
              autofillHints: widget.autofillHints,
            ),
          ),
          if (!widget.isLeftIcon) Icon(widget.icon, color: widget.iconColor),
        ],
      ),
    );
  }
}
