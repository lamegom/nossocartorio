<html>

   <head><title>A Comment Test</title>

</head> 
<body>

<div id="preparing-file-modal" title="Preparing report..." style="display: none;">
            We are preparing your report, please wait...

            <div class="ui-progressbar-value ui-corner-left ui-corner-right" style="width: 100%; height:22px; margin-top: 20px;"></div>
        </div>

        <div id="error-modal" title="Error" style="display: none;">
            There was a problem generating your report, please try again.
        </div>
        <form action="/Demo1" class="fileDownloadForm" method="post">    
    <input type="text" name="cpf" value="42"/> Setting foo to an odd number will cause the file download to fail <br/>
    
    <br/>
    <input type="submit"/>
</form><br/>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
<script src="jquery.fileDownload.js"></script>

<link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/themes/ui-lightness/jquery-ui.css" rel="stylesheet" type="text/css" />
<script>
$(document).ready(function() {
	
	$(document).on("submit", "form.fileDownloadForm", function (e) {
        $.fileDownload($(this).prop('action'), {
            preparingMessageHtml: "We are preparing your report, please wait...",
            failMessageHtml: "There was a problem generating your report, please try again.",
            httpMethod: "POST",
            data: $(this).serialize()
        });
        e.preventDefault(); //otherwise a normal form submit would occur
    });
    
});
</script>
</body>
</html>