<?php
session_start();
if (isset($_GET['logout']) && isset($_GET['userinfo'])) {
    session_destroy();
    _p('ログアウトしました');
    die();
}
if ($_SESSION['userinfo']['userId']) {
    _p('ようこそ ' . $_SESSION['userinfo']['username'] . 'さん');
    die();
}
if (!isset($_GET['code'])) {
    header('Location: http://localhost:8080/oauth/authorize?response_type=code&client_id=child&redirect_uri=http://oauth-callback.com&scope=all');
    die();
}



_p('認可コードを取得: ' . $_GET['code']);
$ch = curl_init();
$url = 'http://child:childpassword@host.docker.internal:8080/oauth/token';
$data = array(
    'grant_type' => 'authorization_code',
    'code' => $_GET['code'],
    'redirect_uri' => 'http://oauth-callback.com',
);
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$result = json_decode(curl_exec($ch));
curl_close($ch);
$access_token = $result->{'access_token'};
_p('アクセストークンを取得: ' . $access_token);

$ch = curl_init();
$url = 'http://host.docker.internal:8080/info';
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_HTTPHEADER, array("Authorization: Bearer $access_token"));
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$result = curl_exec($ch);
curl_close($ch);
_p('ユーザ情報を取得: ' . $result);

$_SESSION['userinfo'] = json_decode($result, true);
_p('ようこそ ' . $_SESSION['userinfo']['username'] . 'さん');

function _p($str) {
    echo '<p>' . $str . '</p>';
}