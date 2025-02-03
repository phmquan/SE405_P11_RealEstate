import 'package:flutter/material.dart';
import 'dart:ui';

class CardButton extends StatelessWidget {
  final String imagePath; 
  final String text1; 
  final String text2; 
  final VoidCallback? onTap; 

  const CardButton({
    super.key,
    required this.imagePath,
    required this.text1,
    required this.text2,
    this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onTap,
      child: Stack(
        children: [
          // Image in the background
          ClipRRect(
            borderRadius: BorderRadius.circular(10),
            child: Image.asset(
              imagePath,
              fit: BoxFit.cover,
              height: 120,
              width: MediaQuery.of(context).size.width * 0.5, 
            ),
          ),
          
          // Blur effect at the top
          Positioned(
            top: 0,
            left: 0,
            right: 0,
            child: Container(
              height: 40, // Adjust height as needed
              decoration: BoxDecoration(
                gradient: LinearGradient(
                  colors: [Colors.black.withOpacity(0.5), Colors.transparent],
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                ),
              ),
            ),
          ),
          
          // Blur effect at the bottom
          Positioned(
            bottom: 0,
            left: 0,
            right: 0,
            child: Container(
              height: 40, // Adjust height as needed
              decoration: BoxDecoration(
                gradient: LinearGradient(
                  colors: [Colors.transparent, Colors.black.withOpacity(0.5)],
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                ),
              ),
            ),
          ),

          // Container with shadow effect
          // Container(
          //   decoration: BoxDecoration(
          //     borderRadius: BorderRadius.circular(10),
          //     boxShadow: [
          //       BoxShadow(
          //         color: Colors.transparent,
          //       ),
          //       BoxShadow(
          //         color: Colors.black.withOpacity(0.5),
          //         blurRadius: 10,
          //         offset: Offset(0, 5), // Shadow at the bottom
          //       ),
          //     ],
          //   ),
          //   height: 120,
          //   width: MediaQuery.of(context).size.width * 0.5, 
          // ),
          

          Positioned(
            top: 5,
            left: 5,
            child: Container(
              padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
              child: Text(
                text1,
                style: const TextStyle(
                  color: Colors.white,
                  fontSize: 12,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ),
          Positioned(
            bottom: 5,
            left: 5,
            child: Container(
              padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
              child: Text(
                text2,
                style: const TextStyle(
                  color: Colors.white,
                  fontSize: 12,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
