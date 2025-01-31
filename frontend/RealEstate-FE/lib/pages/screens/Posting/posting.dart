import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/assets/colors/colors.dart';
import 'package:frontend/widgets/dropdown.dart';

class Posting extends ConsumerStatefulWidget {
  const Posting({super.key});

  @override
  PostingState createState() => PostingState();
}

class PostingState extends ConsumerState<Posting> {
  String dropdownValue = 'Căn hộ/Chung cư';

  void onTypeChanged(String value) {
    setState(() {
      dropdownValue = value;
    });

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "Đăng tin",
          style: TextStyle(
            color: AppColors.white,
            fontSize: 28,
            fontWeight: FontWeight.normal
          ),
        ),
        centerTitle: true,
        backgroundColor: AppColors.blue,
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16),
          child: Column(
            children: [
              Container(
                padding: const EdgeInsets.all(5),
                decoration: BoxDecoration(
                  border: Border.all(color: Colors.blue), // Border color
                  borderRadius: BorderRadius.circular(8), // Border radius for rounded corners
                ),
                child: DropdownButton<String>(
                  value: dropdownValue,
                  underline: const SizedBox(), // Hides the default underline
                  isExpanded: true, // Makes the dropdown button occupy all available width
                  items: const [
                    DropdownMenuItem(value: 'Căn hộ/Chung cư', child: Text('Căn hộ/Chung cư')),
                    DropdownMenuItem(value: 'Nhà ở', child: Text('Nhà ở')),
                    DropdownMenuItem(value: 'Đất', child: Text('Đất')),
                    DropdownMenuItem(value: 'Văn phòng/Mặt bằng kinh doanh', child: Text('Văn phòng/Mặt bằng kinh doanh')),
                    DropdownMenuItem(value: 'Phòng trọ', child: Text('Phòng trọ')),
                  ],
                  onChanged: (value) {
                    if (value != null) {
                      onTypeChanged(value);
                    }
                  },
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}