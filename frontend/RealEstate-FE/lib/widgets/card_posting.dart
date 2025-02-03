import 'package:flutter/material.dart';
import 'package:frontend/assets/colors/colors.dart'; // Assuming custom colors are defined here

class CustomCard extends StatefulWidget {
  final String imagePath; // URL of the image
  final String loaiHinh;
  final String gia;
  final String dientich;
  final String vitri;
  final String thoigian;
  final String tieude;  // Title field (tieude)
  final VoidCallback? onTap;
  final VoidCallback? onSaveTap; // Callback for the Save button

  const CustomCard({
    super.key,
    required this.imagePath,
    required this.loaiHinh,
    required this.gia,
    required this.dientich,
    required this.vitri,
    required this.thoigian,
    required this.tieude, // Initialize the title (tieude)
    this.onTap,
    this.onSaveTap,
  });

  @override
  _CustomCardState createState() => _CustomCardState();
}

class _CustomCardState extends State<CustomCard> {
  Color _saveButtonColor = Colors.blue;
  void _onSaveButtonPressed() {
    setState(() {
      _saveButtonColor = _saveButtonColor == Colors.blue ? Colors.green : Colors.blue;
    });
    if (widget.onSaveTap != null) {
      widget.onSaveTap!();
    }
  }

  @override
  Widget build(BuildContext context) {
    double screenWidth = MediaQuery.of(context).size.width;
    double cardWidth = screenWidth * 0.5;

    return GestureDetector(
      onTap: widget.onTap,
      child: Card(
        elevation: 5,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(12),
        ),
        child: Stack(
          children: [
            Container(
              width: cardWidth,
              padding: const EdgeInsets.all(12.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  // Image
                  Image.asset(
                    widget.imagePath,
                    width: double.infinity, 
                    height: 200,
                    fit: BoxFit.cover,
                  ),
                  const SizedBox(height: 8),
                  
                  // Title (tieude)
                  Text(
                    widget.tieude,
                    style: const TextStyle(
                      fontSize: 14,
                      fontWeight: FontWeight.bold,
                      color: Colors.blue,
                    ),
                    maxLines: 2, 
                    overflow: TextOverflow.ellipsis, 
                    softWrap: true,
                  ),
                  const SizedBox(height: 8),
                  
                  // loaiHinh text with style
                  Text(
                    widget.loaiHinh,
                    style: const TextStyle(
                      fontSize: 10,
                      fontWeight: FontWeight.bold,
                      color: Colors.blue,
                    ),
                  ),
                  const SizedBox(height: 4),
                  
                  // Row for gia and dientich
                  Row(
                    mainAxisAlignment: MainAxisAlignment.start,
                    children: [
                      Text(
                        widget.gia,
                        style: const TextStyle(
                          fontSize: 12, 
                          color: AppColors.red,
                        ),
                      ),
                      const Text(
                        ' - ',
                        style: TextStyle(
                          fontSize: 12, 
                          color: AppColors.black,
                        ),
                      ),
                      Text(
                        widget.dientich,
                        style: const TextStyle(
                          fontSize: 11,
                          color: Colors.black54,
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(height: 8),
                  
                  // Row for vitri and thoigian
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            widget.vitri,
                            style: const TextStyle(
                              fontSize: 10, 
                              color: Colors.black87,
                            ),
                          ),
                          Text(
                            widget.thoigian,
                            style: const TextStyle(
                              fontSize: 10, 
                              color: Colors.grey,
                            ),
                          ),
                        ],
                      ),
                      IconButton(
                        icon: Icon(
                          Icons.save_alt, // Save icon
                          color: _saveButtonColor, // Color changes on press
                          size: 28,
                        ),
                        onPressed: _onSaveButtonPressed, // Handle save button press
                      ),
                    ],
                  )
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
