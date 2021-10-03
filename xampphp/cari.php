<?php
require_once('koneksi.php');
if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    //proses
    $id_crud = $_GET['id_crud'];
    $proses = mysqli_query($con, "SELECT * FROM crudpertama where id_crud = '$id_crud' ");
    if (mysqli_num_rows($proses) > 0) {
        //jika ada data
        $response["success"] = 1;
        $row = mysqli_fetch_array($proses);
        $hasil = array();
        $hasil['id_crud'] = $row['id_crud'];
        $hasil['nama'] = $row['nama'];
        $hasil['jurusan'] = $row['jurusan'];
        $hasil['mapel'] = $row['mapel'];
        $response['data'] = $hasil;
        echo json_encode($response);
    } else {
        //tidak ada data
        $response["success"] = 0;
        $response["pesan"] = "tidak ada data";
        echo json_encode($response);
    }
} else {
    $response["success"] = 0;
    $response["pesan"] = "tidak ada request";
    echo json_encode($response);
}