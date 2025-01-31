import 'package:flutter/material.dart';

class CustomButtonList extends StatelessWidget {
  final int index;
  final bool isSelected;
  final Function(int) onPressed;

  const CustomButtonList({
    super.key,
    required this.index,
    required this.isSelected,
    required this.onPressed,
  });

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: () => onPressed(index),
      child: Text(
        'Button ${index + 1}',
        style: TextStyle(
          color: isSelected ? Colors.blue : Colors.grey,
        ),
      ),
    );
  }
}