import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart';
import 'package:frontend/widgets/card_button.dart'; 


class CustomTabBar extends StatelessWidget {
  const CustomTabBar({super.key});

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 2, // Number of parent tabs
      child: Column(
        children: [
          Align(
            alignment: Alignment.topLeft,
            child: Container(
              width: 200,
              child: const TabBar(
                isScrollable: true,
                padding: EdgeInsets.symmetric(horizontal: 0),
                labelPadding: EdgeInsets.only(right: 20),
                tabAlignment: TabAlignment.start,
                unselectedLabelColor: AppColors.gray,
                dividerColor: Colors.transparent,
                labelStyle: TextStyle(fontSize: 12),
                tabs: [
                  Tab(text: 'Mua bán'),
                  Tab(text: 'Cho thuê'),
                ],
              ),
            ),
          ),
          Container(
            height: 200,
            child: const TabBarView(
              children: [
                // Content for Button 1 tab
                NestedTabBarContent(tabTitle: 'Mua bán'),
                // Content for Button 2 tab
                NestedTabBarContent(tabTitle: 'Cho thuê'),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

class NestedTabBarContent extends StatefulWidget {
  final String tabTitle;

  const NestedTabBarContent({super.key, required this.tabTitle});

  @override
  _NestedTabBarContentState createState() => _NestedTabBarContentState();
}

class _NestedTabBarContentState extends State<NestedTabBarContent> {
  int selectedButtonIndex = 0;

  final Map<String, List<List<Map<String, String>>>> data = {
    'Mua bán': [
      [ // Căn hộ/Chung cư
        {"imagePath": "asset/ha-noi-1.jpg", "text1": "Hà Nội", "text2": "111 tin đăng"},
        {"imagePath": "asset/ha-noi-1.jpg", "text1": "Hà Nội", "text2": "120 tin đăng"},
      ],
      [ // Nhà ở
        {"imagePath": "asset/ha-noi-1.jpg", "text1": "Hà Nội", "text2": "99 tin đăng"},
      ],
      [ // Đất
        {"imagePath": "asset/ha-noi-1.jpg", "text1": "Hà Nội", "text2": "50 tin đăng"},
      ],
      [ // Văn phòng/Mặt bằng
        {"imagePath": "asset/ha-noi-1.jpg", "text1": "Hà Nội", "text2": "35 tin đăng"},
      ],
    ],
    'Cho thuê': [
      [ // Căn hộ/Chung cư
        {"imagePath": "asset/ha-noi-1.jpg", "text1": "Hà Nội", "text2": "200 tin đăng"},
      ],
      [ // Nhà ở
        {"imagePath": "asset/ha-noi-1.jpg", "text1": "Hà Nội", "text2": "150 tin đăng"},
      ],
      [ // Đất
        {"imagePath": "asset/ha-noi-1.jpg", "text1": "Hà Nội", "text2": "75 tin đăng"},
      ],
      [ // Văn phòng/Mặt bằng
        {"imagePath": "asset/ha-noi-1.jpg", "text1": "Hà Nội", "text2": "45 tin đăng"},
      ],
    ]
  };

  void onButtonPressed(int index) {
    setState(() {
      selectedButtonIndex = index;
    });
  }

  

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
          height: 40,
          //color: Colors.blueAccent,
          padding: const EdgeInsets.symmetric(vertical: 5),
          child: ListView(
            scrollDirection: Axis.horizontal,
            children: [
              ElevatedButton(
                onPressed: () => onButtonPressed(0),
                style: ElevatedButton.styleFrom(
                  backgroundColor: selectedButtonIndex == 0 ? AppColors.lightBlue : AppColors.white,
                ),
                child: const Text('Căn hộ/Chung cư'),
              ),
              const SizedBox(width: 10),
              ElevatedButton(
                onPressed: () => onButtonPressed(1),
                style: ElevatedButton.styleFrom(
                  backgroundColor: selectedButtonIndex == 1 ? AppColors.lightBlue : AppColors.white,
                ),
                child: const Text('Nhà ở'),
              ),
              const SizedBox(width: 10),
              ElevatedButton(
                onPressed: () => onButtonPressed(2),
                style: ElevatedButton.styleFrom(
                  backgroundColor: selectedButtonIndex == 2 ? AppColors.lightBlue : AppColors.white,
                ),
                child: const Text('Đất'),
              ),
              const SizedBox(width: 10),
              ElevatedButton(
                onPressed: () => onButtonPressed(3),
                style: ElevatedButton.styleFrom(
                  backgroundColor: selectedButtonIndex == 3 ? AppColors.lightBlue : AppColors.white,
                ),
                child: const Text('Văn phòng/Mặt bằng'),
              ),
            ],
          ),
        ),
        Expanded(
          child: Builder(
            builder: (context) {
              final tabData = data[widget.tabTitle]?[selectedButtonIndex];
                if (tabData != null && tabData.isNotEmpty) {
                  return SingleChildScrollView(
                    scrollDirection: Axis.horizontal,
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.start, // Align to the start of the Row
                      children: tabData.map((item) {
                        // Adding space between each card using Padding
                        return Padding(
                          padding: const EdgeInsets.only(right: 16.0), // Space between cards
                          child: CardButton(
                            imagePath: item['imagePath']!,
                            text1: item['text1']!,
                            text2: item['text2']!,
                          ),
                        );
                      }).toList(),
                    ),
                  );
                } else {
                  return const Center(child: Text("No data available for this category."));
                }
            },
          ),
        ),
      ],
    );
  }
}