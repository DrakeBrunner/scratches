<html>
<head>
<?php
    $srv = "localhost";
    $id = "username";
    $pass = "secrect_pass";
    $dbn = "OTimer";
    $sql = "select username,duration from otimer order by duration desc";

    $db = mysql_connect($srv, $id, $pass);
    mysql_select_db($dbn, $db);
    mysql_query("set names utf8", $db);

    $rs = mysql_query($sql, $db);
?>
<meta charset="utf-8">
<link rel="stylesheet" href="<?php
    # Choose which stylesheet to use
    if (preg_match("/iPhone|iPad|Android/", $_SERVER['HTTP_USER_AGENT']))
        print "style_sp.css";
    elseif ($_SERVER['HTTP_USER_AGENT'] == "OTimerWebView")
        print "style_webview.css";
    else
        print "style.css";
?>" type="text/css" charset="utf-8">
</head>
<body>
<div id="table_wrapper">
    <table <?php
        if (preg_match("/iPhone|iPad|Android/", $_SERVER['HTTP_USER_AGENT'])
            or $_SERVER['HTTP_USER_AGENT'] == "OTimerWebView")
            print "cellspacing=0";
        else
            print "border=1";
    ?>>
    </div>
    <TR>
    <TH id="username_title" class="username">Username</TH>
    <TH id="duration_title" class="duration">Days</TH>
    </TR>

    <?php
        while ($arr = mysql_fetch_row($rs)) {
            print "<TR class=\"data\">";
            $isUsername = 1;
            foreach ($arr as $entry) {
                print $isUsername == 1 ? "<TD class=\"username\">" : "<TD class=\"duration\">" ;
                print "$entry</TD>";

                $isUsername *= -1;
            }
            print "</TR>\n";
        }
    ?>

    </TABLE>
</div>
</body>
</html>
