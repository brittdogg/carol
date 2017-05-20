<!-- jQuery and Bootstrap JS -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<!-- Add custom JS script tag here -->

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/tinymce/js/tinymce/tinymce.min.js"></script>
        <script type="text/javascript">
            tinyMCE.init({
                selector: 'textarea',
                height: 500,
                theme: 'modern',
                plugins: ['jbimages']
            });
        </script>