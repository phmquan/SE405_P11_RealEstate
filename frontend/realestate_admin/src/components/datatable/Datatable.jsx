import React, { useState } from "react";
import { DataGrid } from "@mui/x-data-grid";
import useFetchData from "../../data/useFetchData"; // Adjust path as needed
import { Link } from "react-router-dom";
import "./datatable.scss";
import api from "../../api/AxiosInterceptors";

const Datatable = ({ type }) => {
  // State for pageSize, current page, and selection.
  const [pageSize, setPageSize] = useState(10);
  const [currentPage, setCurrentPage] = useState(1);
  const [selectionModel, setSelectionModel] = useState([]);

  // Determine the API endpoint based on type and include current page and pageSize.
  let endpoint;
  if (type === "user") {
    endpoint = `/admin/user?current=${currentPage}&pageSize=50`;
  } else if (type === "listing") {
    endpoint = `/admin/listing?current=${currentPage}&pageSize=50`;
  } else if (type === "specification") {
    endpoint = `/admin/specification?current=${currentPage}&pageSize=50`;
  }

  // Fetch data from the API using a custom hook, along with the refresh function.
  const { data, loading, error, refreshData } = useFetchData(endpoint);

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
      { field: "nameOnCertification", headerName: "Tên trên chứng chỉ", width: 230 },
      { field: "brokerCertificationNumber", headerName: "Số chứng chỉ", width: 150 },
      { field: "status", headerName: "Trạng thái", width: 100 },
    ];
  }

  // --- BULK ACTION HANDLERS ---

  // For types other than "user"
  const handleBulkAccept = async () => {
    console.log("Selected rows to accept:", selectionModel);
    try {
      const response = await api.post(`admin/${type}/acceptBulk`, selectionModel);
      alert("Bulk accept response: " + JSON.stringify(response.data));
      refreshData();
    } catch (error) {
      console.error("Error accepting items: " + error);
    }
  };

  const handleBulkReject = async () => {
    console.log("Selected rows to reject:", selectionModel);
    try {
      const response = await api.post(`admin/${type}/rejectBulk`, selectionModel);
      alert("Bulk reject response: " + JSON.stringify(response.data));
      refreshData();
    } catch (error) {
      console.error("Error rejecting items: " + error);
    }
  };

  // For type "user" only
  const handleBulkBan = async () => {
    console.log("Selected users to ban: " + JSON.stringify(selectionModel));
    try {
      const response = await api.post(`admin/${type}/banBulk`, selectionModel);
      alert("Bulk ban response: " + JSON.stringify(response.data));
      refreshData();
    } catch (error) {
      console.error("Error banning users: " + error);
    }
  };

  // --- ACTION COLUMN (per-row buttons) ---

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
          {type === "user" ? (
            // For type "user", only show the Ban button
            <div
              className="banButton"
              onClick={async () => {
                try {
                  const response = await api.get(`admin/${type}/${params.row.id}/ban`);
                  console.log("Ban response: " + JSON.stringify(response.data));
                  refreshData();
                } catch (error) {
                  console.log("Error banning user: " + error);
                }
              }}
            >
              Ban
            </div>
          ) : (
            // For other types, show the Accept and Reject buttons
            <>
              <div
                className="acceptButton"
                onClick={async () => {
                  try {
                    const response = await api.get(`admin/${type}/${params.row.id}/accept`);
                    alert("Accept response: " + JSON.stringify(response.data));
                    refreshData();
                  } catch (error) {
                    console.error("Error accepting item: " + error);
                  }
                }}
              >
                Chấp nhận
              </div>
              <div
                className="rejectButton"
                onClick={async () => {
                  try {
                    const response = await api.get(`admin/${type}/${params.row.id}/reject`);
                    alert("Reject response: " + JSON.stringify(response.data));
                    refreshData();
                  } catch (error) {
                    console.error("Error rejecting item: " + error);
                  }
                }}
              >
                Từ chối
              </div>
            </>
          )}
        </div>
      ),
    },
  ];

  // --- RENDERING ---

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error loading data</p>;

  return (
    <div style={{ height: "70vh", width: "100%" }} className="datatable">
      <div className="datatableHead">
        <div className="datatableTitle">
          {type === "user"
            ? "Người dùng"
            : type === "listing"
              ? "Tin đăng chờ duyệt"
              : "Chuyên trang chờ duyệt"}
        </div>
        {type === "user" ? (
          // For "user", display a single bulk action button to ban selected users.
          <div className="bulkActions">
            <div style={{ marginBottom: "10px" }}>
              <button onClick={handleBulkBan} className="ban">
                Ban đã chọn
              </button>
            </div>
          </div>
        ) : (
          // For other types, display both Accept and Reject bulk action buttons.
          <div className="bulkActions">
            <div style={{ marginBottom: "10px" }}>
              <button onClick={handleBulkAccept} className="accept">
                Chấp nhận
              </button>
            </div>
            <div style={{ marginBottom: "10px" }}>
              <button onClick={handleBulkReject} className="reject">
                Từ chối
              </button>
            </div>
          </div>
        )}
      </div>

      <DataGrid
        className="datagrid"
        rows={data}
        columns={baseColumns.concat(actionColumn)}
        pageSize={pageSize}
        onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
        rowsPerPageOptions={[10, 20, 50]}
        pagination
        paginationMode="server"
        page={currentPage - 1}
        onPageChange={(newPage) => setCurrentPage(newPage + 1)}
        checkboxSelection
        onSelectionModelChange={(newSelection) => setSelectionModel(newSelection)}
        selectionModel={selectionModel}
      />
    </div>
  );
};

export default Datatable;
