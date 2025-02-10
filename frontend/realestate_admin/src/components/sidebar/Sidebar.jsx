import "./sidebar.scss";
import DashboardIcon from "@mui/icons-material/Dashboard";
import PersonOutlineIcon from "@mui/icons-material/PersonOutline";

import CreditCardIcon from "@mui/icons-material/CreditCard";
import StoreIcon from "@mui/icons-material/Store";

import ExitToAppIcon from "@mui/icons-material/ExitToApp";

import AccountCircleOutlinedIcon from "@mui/icons-material/AccountCircleOutlined";
import { Link } from "react-router-dom";
import { DarkModeContext } from "../../context/darkModeContext";
import { useContext } from "react";
import api from "../../api/AxiosInterceptors";
const Sidebar = () => {
  const { dispatch } = useContext(DarkModeContext);
  return (
    <div className="sidebar">
      <div className="top">
        <Link to="/" style={{ textDecoration: "none" }}>
          <span className="logo">ESTATERY</span>
        </Link>
      </div>
      <hr />
      <div className="center">
        <ul>
          <p className="title">MAIN</p>
          <li>
            <DashboardIcon className="icon" />
            <span>Dashboard</span>
          </li>
          <p className="title">LISTS</p>
          <Link to="/user" style={{ textDecoration: "none" }} >
            <li>
              <PersonOutlineIcon className="icon" />
              <span>Người dùng</span>
            </li>
          </Link>
          <Link to="/listing" style={{ textDecoration: "none" }} >
            <li>
              <StoreIcon className="icon" />
              <span>Tin đăng</span>
            </li>
          </Link>
          <Link to="/specification" style={{ textDecoration: "none" }} >
            <li>
              <CreditCardIcon className="icon" />
              <span>Chuyên trang</span>
            </li>
          </Link>
          <p className="title">USER</p>
          <li>
            <AccountCircleOutlinedIcon className="icon" />
            <span>Thông tin tài khoản</span>
          </li>
          <li>
            <ExitToAppIcon className="icon" />
            <span
              style={{ cursor: "pointer" }}
              onClick={() => {
                // Gọi API logout
                api.post("/auth/logout")
                  .then(() => {
                    // Xóa localStorage
                    localStorage.clear();

                    // Xóa tất cả cookies (lưu ý: chỉ xóa được cookie có thể truy cập từ JS)
                    document.cookie.split(";").forEach((cookie) => {
                      const eqPos = cookie.indexOf("=");
                      const name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
                      // Đặt lại cookie với thời hạn quá khứ để xóa nó
                      document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/";
                    });

                    // Redirect về trang /login
                    window.location.href = "/login";
                  })
                  .catch((error) => {
                    console.error("Lỗi khi đăng xuất:", error);
                  });
              }}
            >
              Đăng xuất
            </span>
          </li>
        </ul>
      </div>

    </div>
  );
};

export default Sidebar;
