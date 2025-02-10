import Home from "./pages/home/Home";
import Login from "./pages/login/Login";
import List from "./pages/list/List";
import Single from "./pages/single/Single";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import { productInputs, userInputs } from "./formSource";
import "./style/dark.scss";
import { useContext } from "react";
import { DarkModeContext } from "./context/darkModeContext";
import ProtectedRoute from "./ProtectedRoute";

function App() {
  const { darkMode } = useContext(DarkModeContext);

  return (
    <div className={darkMode ? "app dark" : "app"}>
      <BrowserRouter>
        <Routes>
          {/* Public Route */}
          <Route path="/login" element={<Login />} />

          {/* Protected Routes */}
          <Route element={<ProtectedRoute />}>
            <Route path="/" element={<Home />} />
            <Route path="user">
              <Route index element={<List type="user" />} />
              <Route path=":userId" element={<Single type="user" />} />
            </Route>
            <Route path="listing">
              <Route index element={<List type="listing" />} />
              <Route path=":listingId" element={<Single type="listing" />} />
            </Route>
            <Route path="specification">
              <Route index element={<List type="specification" />} />
              <Route path=":specificationId" element={<Single type="specification" />} />
            </Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
