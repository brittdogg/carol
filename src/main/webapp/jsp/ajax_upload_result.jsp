<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <title>JustBoil's Result Page</title>
        <script language="javascript" type="text/javascript">
    window.parent.window.jbImagesDialog.uploadFinish({
        filename: "${image.filename}",
        result: "${image.result}",
        resultCode: "${image.status}"
    });
        </script>
        <style type="text/css">
            body {font-family: Courier, "Courier New", monospace; font-size:11px;}
        </style>
    </head>

    <body>

        Result: ${image.result}

    </body>
</html>
