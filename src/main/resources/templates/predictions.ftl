
<head>
    <title>predictor</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="shortcut icon" type="image/x-icon"
          href="https://i.pinimg.com/originals/17/cc/b8/17ccb8e702c3f71dbb4bf08891d009f5.png">
</head>

<body>

<img src="${pictureURL}" alt="loaded picture">
<table>

    <#if (predictionListSize == 0)>
        no predictions
    <#else>
        <tr>
            <td> date of image loading: ${imgLoadDate} </td>
        </tr>
        <tr>
            <td> all the predictions: </td>
        </tr>
        <tr>
            <td>prediction name</td>
            <td>prediction value</td>
        </tr>

    </#if>

    <#foreach prediction in predictionList>
        <tr>
            <td>${prediction.getPredictionName()}</td>
            <td>${prediction.getPredictionValue()}</td>
        </tr>
    </#foreach>
</table>

</body>