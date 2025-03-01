
import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart';
import 'package:frontend/assets/icons/icons.dart';


class CustomDropdown extends StatelessWidget {
  final List<String> items;
  final String selectedItem;
  final ValueChanged<String?>? onChanged;
  final double? dropdownHeight;
  final double? dropdownWidth;
  final double? borderRadius;

  const CustomDropdown({
    super.key,
    required this.items,
    required this.selectedItem,
    this.onChanged,
    this.dropdownHeight,
    this.dropdownWidth,
    this.borderRadius = 10,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      height: dropdownHeight,
      width: dropdownWidth,
      decoration: BoxDecoration(
        color: AppColors.lightGrey,
        borderRadius: BorderRadius.circular(10),
      ),
      padding: const EdgeInsets.symmetric(horizontal: 10),
      child: DropdownButton<String>(
        value: selectedItem,
        onChanged: onChanged,
        underline: const SizedBox(),
        icon: const Icon(AppIcons.dropdown, color: AppColors.black),
        iconSize: 24,
        isExpanded: true,
        style: const TextStyle(
          color: Colors.black,
          fontFamily: 'Mulish',
          fontSize: 14,
        ),
        items: items.map((String value) {
          return DropdownMenuItem<String>(
            value: value,
            child: Text(value),
          );
        }).toList(),
      ),
    );
  }
}
