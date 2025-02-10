import "./single.scss";
import Sidebar from "../../components/sidebar/Sidebar";
import Navbar from "../../components/navbar/Navbar";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import api from "../../api/AxiosInterceptors";

const Single = ({ type }) => {
  // Get the id from the URL (it could be named userId, listingId, or specificationId)
  const { userId, listingId, specificationId } = useParams();
  const id = userId || listingId || specificationId;

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch data from the API
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get(`/admin/${type}/${id}`);
        // Assuming your API returns { data: { ... } } in response.data.data
        setData(response.data.data);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [type, id]);

  if (loading) {
    return <div>Đang tải...</div>;
  }

  if (error) {
    return <div>Lỗi: {error.message}</div>;
  }

  if (!data) {
    return <div>Không tìm thấy dữ liệu</div>;
  }

  // Render user details based on the user JSON structure.
  const renderUserDetails = () => (

    <>

      <img
        src={
          data.avatar
            ? data.avatar
            : "https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png?20150327203541"
        }
        alt="User Avatar"
        className="itemImg"
      />

      <div className="details">
        <h1 className="itemTitle">Chi tiết người dùng {userId}</h1>
        <div className="detailItem">
          <span className="itemKey">Email:</span>
          <span className="itemValue">{data.email}</span>
        </div>
        <div className="detailItem">
          <span className="itemKey">Tuổi:</span>
          <span className="itemValue">{data.age || "N/A"}</span>
        </div>
        <div className="detailItem">
          <span className="itemKey">Address:</span>
          <span className="itemValue">{data.address || "N/A"}</span>
        </div>
        <div className="detailItem">
          <span className="itemKey">Số điện thoại:</span>
          <span className="itemValue">{data.phone}</span>
        </div>
        <div className="detailItem">
          <span className="itemKey">Status:</span>
          <span className="itemValue">{data.status}</span>
        </div>
      </div>
    </>
  );

  // Render listing details based on the listing JSON structure.
  const renderListingDetails = () => (
    <div className="details">
      <h1 className="itemTitle">Chi tiết bài đăng {listingId}</h1>
      <div className="detailItem">
        <span className="itemKey">Người đăng:</span>
        <span className="itemValue">{data.userEmail}</span>
      </div>
      <div className="detailItem">
        <span className="itemKey">Loại bất động sản:</span>
        <span className="itemValue">{data.propertyType}</span>
      </div>
      <div className="detailItem">
        <span className="itemKey">Listing Type:</span>
        <span className="itemValue">{data.listingType}</span>
      </div>
      {data.property && (
        <>
          <h2 className="subTitle">Chi tiết bất động sản trong bài</h2>
          <div className="detailItem">
            <span className="itemKey">Địa chỉ:</span>
            <span className="itemValue">{data.property.address}</span>
          </div>
          <div className="detailItem">
            <span className="itemKey">Quận:</span>
            <span className="itemValue">{data.property.district}</span>
          </div>
          <div className="detailItem">
            <span className="itemKey">Diện tích bất động sản:</span>
            <span className="itemValue">{data.property.propertyArea}</span>
          </div>
          <div className="detailItem">
            <span className="itemKey">Giấy tờ pháp lý:</span>
            <span className="itemValue">{data.property.legalDocument}</span>
          </div>
        </>
      )}
    </div>
  );

  // Render specification details based on the specification JSON structure.
  const renderSpecificationDetails = () => (
    <div className="details">
      <h1 className="itemTitle">Chi tiết chuyên trang {specificationId}</h1>
      <div className="detailItem">
        <span className="itemKey"> Tên chuyên trang:</span>
        <span className="itemValue">{data.pageName}</span>
      </div>
      <div className="detailItem">
        <span className="itemKey">Họ tên chủ trang:</span>
        <span className="itemValue">{data.fullName}</span>
      </div>
      <div className="detailItem">
        <span className="itemKey">Khu vực môi giới:</span>
        <span className="itemValue">{data.brokerArea}</span>
      </div>
      <div className="detailItem">
        <span className="itemKey">Nơi công tác:</span>
        <span className="itemValue">{data.workingPlaceAdress}</span>
      </div>
      {data.brokerCertification && (
        <>
          <p className="subTitle">Chứng chỉ môi giới</p>
          <div className="detailItem">
            <span className="itemKey">Tên trên chứng chỉ:</span>
            <span className="itemValue">{data.brokerCertification.nameOnCertification}</span>
          </div>
          <div className="detailItem">
            <span className="itemKey">Số chứng chỉ:</span>
            <span className="itemValue">{data.brokerCertification.certificationNumber}</span>
          </div>
          <div className="detailItem">
            <span className="itemKey">Nơi cấp chứng chỉ:</span>
            <span className="itemValue">{data.brokerCertification.certificationAuthority}</span>
          </div>
        </>
      )}
    </div>
  );

  // Choose the appropriate render method based on the type.
  const renderDetails = () => {
    if (type === "user") {
      return renderUserDetails();
    } else if (type === "listing") {
      return renderListingDetails();
    } else if (type === "specification") {
      return renderSpecificationDetails();
    } else {
      return <div>Unknown type</div>;
    }
  };

  return (
    <div className="single">
      <Sidebar />
      <div className="singleContainer">
        <Navbar />
        <div className="top">
          <div className="left">
            <div className="editButton">Edit</div>
            {type === "user" && (
              <h1 className="title">Thông tin người dùng</h1>
            )}
            {type === "listing" && (
              <h1 className="title">Thông tin bài đăng</h1>
            )}
            {type === "specification" && (
              <h1 className="title">Thông tin mô tả chuyên trang</h1>
            )}
            <div className="item">{renderDetails()}</div>
          </div>
          {/* Add more sections (like a right panel) if needed */}
        </div>
      </div>
    </div>
  );
};

export default Single;
