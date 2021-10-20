<?php
require_once('koneksi.php');
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    //input 
    $nama = $_POST['nama'];
    $mapel = $_POST['mapel'];
    $khdr = $_POST['kehadiran'];
    //proses
    $proses = "INSERT INTO crudpertama (nama,mapel,kehadiran) VALUES ('$nama','$mapel','$khdr')";
    //output
    if (mysqli_query($con, $proses)) {
        //true
        $response["success"] = 1;
        $response["pesan"] = "berhasil";
        echo json_encode($response);
    } else {
        //false
        $response["success"] = 0;
        $response["pesan"] = "gagal";
        echo json_encode($response);
    }
} else {
    $response["success"] = 0;
    $response["pesan"] = "tidak ada request";
    echo json_encode($response);
}