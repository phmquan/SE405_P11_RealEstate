import React, { useState } from "react";
import { DataGrid } from "@mui/x-data-grid";
import useFetchData from "../../data/useFetchData"; // adjust path as needed
import { Link } from "react-router-dom";
import "./datatable.scss";
const Datatable = ({ type }) => {
  // State for pageSize and current page.
  const [pageSize, setPageSize] = useState(10);
  // Using 1-indexed page for the endpoint.
  const [currentPage, setCurrentPage] = useState(1);

  // Determine the API endpoint based on type and include current page and pageSize
  let endpoint;
  if (type === "user") {
    endpoint = `/admin/user?current=${currentPage}&pageSize=50`;
  } else if (type === "listing") {
    endpoint = `/admin/listing?current=${currentPage}&pageSize=50`;
  } else if (type === "specification") {
    endpoint = `/admin/specification?current=${currentPage}&pageSize=50`;
  }

  // Fetch data from the API using a custom hook
  const { data, loading, error } = useFetchData(endpoint);

  // Define the base columns for each type.
  let baseColumns = [];
  if (type === "user") {
    baseColumns = [
      { field: "id", headerName: "ID", width: 70 },
      { field: "email", headerName: "Email", width: 230 },
      { field: "name", headerName: "Tên", width: 250 },
      { field: "phone", headerName: "Số điện thoại", width: 200 },
      { field: "status", headerName: "Trạng Thái", width: 100 },
    ];
  } else if (type === "listing") {
    baseColumns = [
      { field: "id", headerName: "ID", width: 70 },
      { field: "propertyType", headerName: "Loại hình bất động sản", width: 150 },
      { field: "businessType", headerName: "Loại hình kinh doanh", width: 150 },
      { field: "price", headerName: "Giá", width: 250 },
      { field: "userListing", headerName: "Người đăng (email)", width: 230 },
      { field: "status", headerName: "Trạng thái", width: 100 },
    ];
  } else if (type === "specification") {
    baseColumns = [
      { field: "id", headerName: "ID", width: 70 },
      { field: "name", headerName: "Tên chuyên trang", width: 230 },
      { field: "description", headerName: "Mô tả", width: 230 },
      { field: "nameOneCertification", headerName: "Tên trên chứng chỉ", width: 230 },
      { field: "brokerCertificationNumber", headerName: "Số chứng chỉ", width: 150 },
      { field: "status", headerName: "Trạng thái", width: 100 },
    ];
  }

  // Define an action column that will be appended to your base columns.
  const actionColumn = [
    {
      field: "action",
      headerName: "Action",
      width: 300,
      renderCell: (params) => (
        <div className="cellAction">
          <Link to={`/${type}/${params.row.id}`}>
            <div className="detailButton">Xem chi tiết</div>
          </Link>
          <div
            className="acceptButton"
            onClick={() => {
              // Handle the accept action here.
            }}
          >
            Chấp nhận
          </div>
          <div
            className="rejectButton"
            onClick={() => {
              // Handle the reject action here.
            }}
          >
            Từ chối
          </div>
        </div>
      ),
    },
  ];

  // Handle loading and error states.
  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error loading data</p>;

  return (
    <div style={{ height: "70vh", width: "100%" }} className="datatable">
      <div className="datatableTitle">
        {type === "user"
          ? "Người dùng"
          : type === "listing"
            ? "Tin đăng chờ duyệt"
            : "Chuyên trang chờ duyệt"}
      </div>
      <DataGrid
        className="datagrid"
        rows={data}
        columns={baseColumns.concat(actionColumn)}
        pageSize={pageSize}
        onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
        rowsPerPageOptions={[10, 20, 50]}
        // Enable server-side pagination:
        pagination
        paginationMode="server"
        // DataGrid pages are zero-indexed, so subtract 1 when setting the page.
        page={currentPage - 1}
        onPageChange={(newPage) => setCurrentPage(newPage + 1)}
        checkboxSelection
      />
    </div>
  );
};

export default Datatable;
