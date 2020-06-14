<%@ include file="../../../frame/base_tags.jsp"%>
<!-- 前端插件 -->
<div class="block-area">
    <select data-placeholder="Select Users..." class="tag-select" multiple>
        <!-- disabled控制不可选 -->
        <option value="David Becham" disabled>David Becham</option>
        <option value="Christian Bale" disabled>Christian Bale</option>
        <option value="Malinda Hollaway" disabled>Malinda Hollaway</option>
        <option value="Jason Stathom">Jason Stathom</option>
        <option value="Wen De Soza">Wen De Soza</option>
        <option value="Jhon Morrison">Jhon Morrison</option>
        <option value="William Gale">William Gale</option>
        <option value="Mark Hakngtosh">Mark Hakngtosh</option>
        <option value="Angola Jenolia">Angola Jenolia</option>
        <option value="William Jansen">William Jansen</option>
        <option value="Kat Steven">Kat Steven</option>
        <option value="Henry Hadson">Henry Hadson</option>
        <option value="Joshep Fernandez">Joshep Fernandez</option>
        <option value="Armani Jens">Armani Jens</option>
        <option value="Wen Diopal">Wen Diopal</option>
        <option value="Aura Moringson">Aura Moringson</option>
        <option value="Peter Robinson">Peter Robinson</option>
        <option value="Dave Watmore">Dave Watmore</option>
        <option value="Jordan Orlendo">Jordan Orlendo</option>
        <option value="Christopher Nolen">Christopher Nolen</option>
    </select>
</div>

<!-- 执行js -->
<script>
    globalJs(function(){
        $('select').chosen();
    })
    /** ********************************************** */
</script>
