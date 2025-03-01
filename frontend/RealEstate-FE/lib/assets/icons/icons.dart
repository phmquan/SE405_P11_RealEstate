import 'package:flutter/material.dart';

class AppIcons {
  static const IconData map = Icons.map;
  static const IconData newspaper = Icons.newspaper;
  static const IconData barChart = Icons.bar_chart;
  static const IconData person = Icons.person_outline;
  static const IconData search = Icons.search;
  static const IconData filter = Icons.filter_alt;
  static const IconData add_location = Icons.add_location_alt_outlined;
  static const IconData location = Icons.location_on_rounded;
  static const IconData email = Icons.email_outlined;
  static const IconData lock = Icons.lock_outline;
  static const IconData calendar = Icons.calendar_month_outlined;
  static const IconData help = Icons.help_outline;
  static const IconData logout = Icons.logout;
  static const IconData dropdown = Icons.keyboard_arrow_down_rounded;
  static const IconData visibility = Icons.visibility_outlined;
  static const IconData visibilityOff = Icons.visibility_off_outlined;
  static const IconData left_arrow = Icons.arrow_back;
  static const IconData edit = Icons.edit_outlined;
  static const IconData camera = Icons.photo_camera_outlined;
  static const IconData delete = Icons.delete_outlined;
  static const IconData back = Icons.arrow_back_ios;
  static const IconData close = Icons.close;
  static const IconData menu = Icons.menu;
  static const IconData share = Icons.emergency_share_outlined;
  static const IconData phone = Icons.phone;
  static const IconData home = Icons.home;
  static const IconData list = Icons.list_alt_rounded;
  static const IconData chat = Icons.chat;

  
  static const double defaultSize = 24.0;

  static Icon getIcon(IconData iconData,
      {double size = defaultSize, Color? color}) {
    return Icon(
      iconData,
      size: size,
      color: color,
    );
  }
}
