import Sidebar from "../../components/sidebar/Sidebar";
import Navbar from "../../components/navbar/Navbar";
import "./home.scss";
import Widget from "../../components/widget/Widget";
import api from "../../api/AxiosInterceptors";
import { useEffect, useState } from "react";

const Home = () => {
  // Khởi tạo state với kiểu object và các giá trị mặc định
  const [data, setData] = useState({
    userNumber: 0,
    listingNumber: 0,
    specificationNumber: 0,
  });
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await api.get("/admin");
        setData(res.data.data);
        setError(null);
      } catch (error) {
        setData({
          userNumber: 0,
          listingNumber: 0,
          specificationNumber: 0,
        });
        setError(error);
      }
    };
    fetchData();
  }, []);

  // Logging dữ liệu mỗi khi có sự thay đổi của data
  useEffect(() => {
    console.log(data);
  }, [data]);

  return (
    <div className="home">
      <Sidebar />
      <div className="homeContainer">
        <Navbar />
        <div className="widgets">
          <Widget type="user" data={data.userNumber} />
          <Widget type="listings" data={data.listingNumber} />
          <Widget type="specifications" data={data.specificationNumber} />
        </div>
      </div>
    </div>
  );
};

export default Home;
