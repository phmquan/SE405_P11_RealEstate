import 'package:flutter/material.dart';

class CustomSection extends StatelessWidget {
  final String title;
  final Widget body;

  const CustomSection({
    super.key, 
    required this.title, 
    required this.body
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 8.0),
      child: Container(
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.circular(8.0),
          boxShadow: [
            // BoxShadow(
            //   color: Colors.grey.withOpacity(0.2),
            //   blurRadius: 5.0,
            //   offset: const Offset(0, 2),
            // ),
          ],
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Padding(
              padding: const EdgeInsets.all(0),
              child: Text(
                title,
                style: const TextStyle(
                  fontSize: 14,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(0),
              child: body,
            ),
          ],
        ),
      ),
    );
  }
}
