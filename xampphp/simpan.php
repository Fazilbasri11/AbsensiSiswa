<?php
require_once('koneksi.php');
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    //input 
    $nama = $_POST['nama'];
    $jk = $_POST['jurusan'];
    $mapel = $_POST['mapel'];
    //proses
    $proses = "INSERT INTO crudpertama (nama,jurusan,mapel) VALUES ('$nama','$jk','$mapel')";
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