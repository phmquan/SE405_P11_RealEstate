import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart';
import 'package:frontend/widgets/Button/Button.dart';
import 'package:frontend/widgets/card_posting.dart';

class ChoThueBDS extends StatefulWidget {
  const ChoThueBDS({super.key});

  @override
  State<ChoThueBDS> createState() => _ChoThueBDSState();
}

class _ChoThueBDSState extends State<ChoThueBDS> {

  final List<Map<String, String>> data = [
    {
      'imagePath': 'asset/hcm-1.jpg',
      'loaiHinh': 'House',
      'gia': '\$500,000',
      'dientich': '200 m²',
      'vitri': 'Downtown',
      'thoigian': '3 days ago',
      'tieude': 'Beautiful house in the city',
    },
    {
      'imagePath': 'asset/hcm-1.jpg',
      'loaiHinh': 'Apartment',
      'gia': '\$350,000',
      'dientich': '150 m²',
      'vitri': 'Suburb',
      'thoigian': '1 week ago',
      'tieude': 'Modern apartment for sale',
    },
    // Add more sample data here
  ];

  @override
  Widget build(BuildContext context) {
    return Column(
        children: [
          // Horizontally scrollable cards
          SingleChildScrollView(
            scrollDirection: Axis.horizontal,
            child: Row(
              children: data.map((item) {
                return CustomCard(
                  imagePath: item['imagePath']!,
                  loaiHinh: item['loaiHinh']!,
                  gia: item['gia']!,
                  dientich: item['dientich']!,
                  vitri: item['vitri']!,
                  thoigian: item['thoigian']!,
                  tieude: item['tieude']!,
                  onSaveTap: () {
                    // handle save action here
                  },
                );
              }).toList(),
            ),
          ),
          CustomButton(
            onTap: () {

            },
            btnText: 'Xem thêm tin khác', 
            btnTextColor: AppColors.black,
            btnColor: AppColors.white,
            borderColor: AppColors.black,
            btnWidth: 200,
          )
        ],
      );

  }
}