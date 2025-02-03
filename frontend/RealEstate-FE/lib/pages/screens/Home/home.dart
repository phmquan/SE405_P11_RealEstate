// ignore_for_file: avoid_print

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:frontend/assets/colors/colors.dart';
import 'package:frontend/pages/screens/Home/bds_chothue.dart';
import 'package:frontend/pages/screens/Home/bds_khuvuc.dart';
import 'package:frontend/pages/screens/Home/bds_muaban.dart';
import 'package:frontend/widgets/Button/TextButton.dart';
import 'package:frontend/widgets/custom_section.dart';
import 'package:frontend/widgets/search_bar';

class Home extends ConsumerStatefulWidget {
  const Home({super.key});

  @override
  HomeState createState() => HomeState();
}

class HomeState extends ConsumerState<Home> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "Trang chủ",
          style: TextStyle(
            color: AppColors.white,
            fontSize: 28,
            fontWeight: FontWeight.normal
          ),
        ),
        centerTitle: true,
        backgroundColor: AppColors.blue,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: SingleChildScrollView( 
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // Search Widget
              SearchWidget(
                onSearchChanged: (searchText) {
                  print('Tìm kiếm: $searchText');
                },
                onTypeChanged: (searchType) {
                  print('Loại tìm kiếm: $searchType');
                },
              ),
              const SizedBox(height: 10), 
              Container(
                padding: const EdgeInsets.symmetric(vertical: 8.0),
                height: 100,  
                //color: Colors.amber,
                child: ListView(
                  scrollDirection: Axis.horizontal,  
                  children: <Widget>[
                    CustomTextButton(
                      onTap: () {},
                      imagePath: 'asset/mua.png',  
                      btnText: 'Mua bán',
                    ),
                    const SizedBox(width: 50),
                    CustomTextButton(
                      onTap: () {},
                      imagePath: 'asset/ban.png', 
                      btnText: 'Cho thuê',
                    ),
                    const SizedBox(width: 50),
                    CustomTextButton(
                      onTap: () {},
                      imagePath: 'asset/duan.png',  
                      btnText: 'Dự án',
                    ),
                    const SizedBox(width: 50),
                    CustomTextButton(
                      onTap: () {},
                      imagePath: 'asset/moigioi.png',  
                      btnText: 'Môi giói',
                    ),
                  ],
                ),
              ),

              // Section 1
              const CustomSection(
                title: 'Bất động sản theo khu vực',
                body: CustomTabBar(),
              ),
              const SizedBox(height: 10), 
              const CustomSection(
                title: 'Mua bán bất động sản',
                body: MuaBanBDS(),
              ),
              const SizedBox(height: 10), 
              const CustomSection(
                title: 'Cho thuê bất động sản',
                body: ChoThueBDS()
              ),
              const SizedBox(height: 10), 
              const CustomSection(
                title: 'Dự án được quan tâm',
                body: Column(
                  children: [
                    Text('Add your widgets or content here for Thue'),
                  ],
                ),
              ),
              const SizedBox(height: 10), 
              const CustomSection(
                title: 'Chuyên trang môi giới Bất động sản',
                body: Column(
                  children: [
                    Text('Add your widgets or content here for Thue'),
                  ],
                ),
              ),
              const SizedBox(height: 10), 
              

            ],
          ),
        ),
      ),
    );
  }


  
}
