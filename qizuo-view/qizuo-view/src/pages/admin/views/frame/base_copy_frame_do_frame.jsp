<%@ include file="../frame/base_tags.jsp"%>
<!-- **************************************************修饰************************************************** -->
<!-- row样式会在左右进行margin,而且是负数 -->
<!-- m-b-10后面的数字表示margin-bottom间隔的距离;m-t-10后面数字表示margin-top的值;后面以此类推还有两种样式 -->
<!-- col-lg-10对表单元素没有作用可在外面装配上，一行12格 -->
<!-- block-area对一个模块进行区分，一般当做一个容器使用 -->
<!-- p-relative代表relative定位 -->
<div class="block-area col-lg-12 m-b-10 <%--row--%>">
	<!-- 标题，类似于label -->
	<h3 class="block-title m-b-10">修饰</h3>
	<br/>
	<!-- icon 主要作用是class="icon" 然后在两个标签中间有icon代码 -->
	<span class="icon m-b-10">&#61697;</span><span>#61697;</span>
	<!-- alert 四种颜色 如果需要图标则加上alert-icon 然后加上icon在最后面 -->
	<div class="alert alert-success alert-icon">
		Well done! You successfully read this important alert message.
		<i class="icon">&#61845;</i>
	</div>
	<div class="alert alert-info alert-icon">
		Heads up! This alert needs your attention, but it's not super important.
		<i class="icon">&#61770;</i>
	</div>
	<div class="alert alert-warning alert-icon">
		Warning! Best check yo self, you're not looking too good.
		<i class="icon">&#61730;</i>
	</div>
	<div class="alert alert-danger alert-icon">
		Oh snap! Change a few things up and try submitting again.
		<i class="icon">&#61907;</i>
	</div>
	<!-- 能删除的alert -->
	<div class="alert alert-info alert-dismissable fade in">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		Heads up! This alert needs your attention, but it's not super important.
	</div>
	<!-- 空白分隔，和线分隔是一个道理 -->
	<div class="clearfix"></div>
	<!-- 线分隔 -->
	<hr class="whiter"/>
</div>

<!-- **************************************************form************************************************** -->
<!-- 表单元素上面都带上form-control这个样式，才会进行框架模型渲染 -->
<div class="block-area m-b-10">
	<!-- 标题，类似于label -->
	<h3 class="block-title">表单</h3>

	<div class="row col-lg-12 m-b-30">
		<h2>input</h2>
		<!-- input-lg/input-sm 表示表单元素input的大小，不写表示default -->
		<!-- input-focused 这个样式标识input focused之后的样式 -->
		<!-- disabled的样式加上disabled即可 -->
		<input type="text" class="form-control m-b-10" value="" placeholder="Default">
		<input type="text" class="form-control m-b-10" value="This is disabled..." disabled placeholder="">
		<input type="text" class="form-control input-focused m-b-20" value="This is disabled..." placeholder="">

		<!-- 几种特殊的input(用mask插件来限制格式) -->
		<div class="col-md-3 m-b-15">
			<label>Date</label>
			<input type="text" class="input-sm form-control mask-date" placeholder="...">
		</div>
		<div class="col-md-3 m-b-15">
			<label>Time</label>
			<input type="text" class="input-sm form-control mask-time" placeholder="...">
		</div>
		<div class="col-md-3 m-b-15">
			<label>Date Time</label>
			<input type="text" class="input-sm form-control mask-date_time" placeholder="...">
		</div>
		<div class="col-md-3 m-b-15">
			<label>CEP</label>
			<input type="text" class="input-sm form-control mask-cep" placeholder="...">
		</div>
		<div class="col-md-3 m-b-15">
			<label>Phone Number</label>
			<input type="text" class="input-sm form-control mask-phone" placeholder="...">
		</div>
		<div class="col-md-3 m-b-15">
			<label>Phone with Odd</label>
			<input type="text" class="input-sm form-control mask-phone_with_ddd" placeholder="...">
		</div>
		<div class="col-md-3 m-b-15">
			<label>US Phone Number</label>
			<input type="text" class="input-sm form-control mask-phone_us" placeholder="...">
		</div>
		<div class="col-md-3 m-b-15">
			<label>Mixed</label>
			<input type="text" class="input-sm form-control mask-mixed" placeholder="...">
		</div>
		<div class="col-md-3 m-b-15">
			<label>CPF</label>
			<input type="text" class="input-sm form-control mask-cpf" placeholder="...">
		</div>
		<div class="col-md-3 m-b-15">
			<label>Money</label>
			<input type="text" class="input-sm form-control mask-money" placeholder="...">
		</div>
		<div class="col-md-3 m-b-15">
			<label>Money 2</label>
			<input type="text" class="input-sm form-control mask-money2" placeholder="...">
		</div>
		<div class="col-md-3 m-b-15">
			<label>IP Address</label>
			<input type="text" class="input-sm form-control mask-ip_address" placeholder="...">
		</div>
		<div class="col-md-3 m-b-15">
			<label>Percentage</label>
			<input type="text" class="input-sm form-control mask-percent" placeholder="...">
		</div>
		<div class="col-md-3 m-b-20">
			<label>Credit Card</label>
			<input type="text" class="input-sm form-control mask-credit_card" placeholder="...">
		</div>

		<div class="clearfix"></div>

		<!-- 数字加减控制 spinedit控制 剩下总共六种样式 spinner-6 -->
		<div class="col-md-3 m-b-15">
			<input type="text" class="form-control input-sm spinner-1 spinedit" />
		</div>
		<div class="col-md-3 m-b-15">
			<input type="text" class="form-control input-sm spinner-2 spinedit" />
		</div>
		<div class="col-md-3 m-b-15">
			<input type="text" class="form-control input-sm spinner-3 spinedit" />
		</div>
		<div class="col-md-3 m-b-15">
			<input type="text" class="form-control input-sm spinner-4 spinedit" />
		</div>
		<div class="col-md-3 m-b-15">
			<input type="text" class="form-control input-sm spinner-5 spinedit" />
		</div>
		<div class="col-md-3 m-b-20">
			<input type="text" class="form-control input-sm spinner-6 spinedit" />
		</div>

		<div class="clearfix"></div>

		<!-- 日期 -->
		<div class="col-md-4 m-b-15">
			<p>Date Picker</p>
			<div class="input-icon datetime-pick date-only">
				<input data-format="dd/MM/yyyy" type="text" class="form-control input-sm" />
				<span class="add-on" style="height: 100%">
					<i class="sa-plus"></i>
				</span>
			</div>
		</div>
		<div class="col-md-4 m-b-15">
			<p>24hr Time Picker</p>
			<div class="input-icon datetime-pick time-only">
				<input data-format="hh:mm:ss" type="text" class="form-control input-sm" />
				<span class="add-on" style="height: 100%">
					<i class="sa-plus"></i>
				</span>
			</div>
		</div>
		<div class="col-md-4 m-b-15">
			<p>12hr Time Picker</p>
			<div class="input-icon datetime-pick time-only-12">
				<input data-format="hh:mm:ss" type="text" class="form-control input-sm" />
				<span class="add-on" style="height: 100%">
					<i class="sa-plus"></i>
				</span>
			</div>
		</div>

		<!-- 颜色选择器 -->
		<div class="col-md-4 m-b-15">
			<p>Default - hex</p>
			<div class="color-pick input-icon">
				<input class="form-control color-picker input-sm" type="text" />
				<span class="color-preview"></span>
				<span class="add-on" style="height: 100%">
					<i class="sa-plus"></i>
				</span>
			</div>
		</div>
		<div class="col-md-4 m-b-15">
			<p>RGB</p>
			<div class="color-pick input-icon">
				<input class="form-control color-picker-rgb input-sm" type="text" />
				<span class="color-preview"></span>
				<span class="add-on" style="height: 100%">
					<i class="sa-plus"></i>
				</span>
			</div>
		</div>
		<div class="col-md-4 m-b-15">
			<p>RGBA</p>
			<div class="color-pick input-icon">
				<input class="form-control color-picker-rgba input-sm" type="text" />
				<span class="color-preview"></span>
				<span class="add-on" style="height: 100%">
					<i class="sa-plus"></i>
				</span>
			</div>
		</div>

		<!-- 进度条 -->
		<!-- 横着进度条 -->
		<input type="text" class="input-slider">
		<p></p>

		<p>Advanced - Orientation, Min Value, Max Value, Default Value, Increment Step and Slider Selection</p>
		<input type="text" class="input-slider" data-slider-min="-20" data-slider-max="20" data-slider-step="1" data-slider-value="-14" data-slider-orientation="horizontal" data-slider-selection="after">
		<p></p>

		<p>Range Selector</p>
		<input type="text" class="input-slider" data-slider-min="10" data-slider-max="1000" data-slider-step="5" data-slider-value="[250,450]">
		<p></p>

		<p>No Tooltip</p>
		<input type="text" class="input-slider" data-slider-tooltip="hide">

		<div class="slider-container">
			<input type="text" class="input-slider" data-slider-min="0" data-slider-max="2000" data-slider-value="800">
			<div class="row">
				<div class="col-md-2 pull-right">
					<input type="text" value="800" class="form-control input-sm slider-value">
				</div>
			</div>
		</div>

		<!-- 竖着进度条 -->
		<input type="text" class="input-slider" value="" data-slider-min="-20" data-slider-max="20" data-slider-value="-15" data-slider-orientation="vertical" data-slider-selection="after">
		<input type="text" class="input-slider" value="" data-slider-min="-20" data-slider-max="20" data-slider-value="14" data-slider-orientation="vertical" data-slider-selection="after">
		<input type="text" class="input-slider" value="" data-slider-min="-20" data-slider-max="20" data-slider-value="-2" data-slider-orientation="vertical" data-slider-selection="after">
		<input type="text" class="input-slider" value="" data-slider-min="-20" data-slider-max="20" data-slider-value="-11" data-slider-orientation="vertical" data-slider-selection="after">
		<input type="text" class="input-slider" value="" data-slider-min="-20" data-slider-max="20" data-slider-value="8" data-slider-orientation="vertical" data-slider-selection="after">
	</div>

	<div class="row col-lg-12 m-b-30">
		<h2>checkbox</h2>
		<!-- 竖的多选(checked属性控制是否选择) -->
		<div class="checkbox m-b-5">
			<label>
				<input type="checkbox" checked>
				This is an awesome sample Checkbox
			</label>
		</div>
		<div class="checkbox m-b-5">
			<label>
				<input type="checkbox">
				This is another awesome sample Checkbox
			</label>
		</div>
		<!-- disabled -->
		<div class="checkbox m-b-5">
			<label>
				<input type="checkbox" checked disabled>
				This is an awesome sample Checkbox checked and disabled
			</label>
		</div>

		<!-- 横的多选(checked属性控制控制是否选择)，注意和上述竖的进行对比 -->
		<label class="checkbox-inline">
			<input type="checkbox">
			1
		</label>
		<label class="checkbox-inline">
			<input type="checkbox">
			2
		</label>

		<!-- 开关组 make-switch类进行控制 -->
		<!-- 开关大小switch-large  switch-small switch-mini -->
		<!-- data-on和data-off控制开关类，如下，最终生成的元素上面，左侧span上会有switch-info，右侧就会有switch-success -->
		<div class="make-switch switch-large" data-on="info" data-off="success">
			<input type="checkbox">
		</div>
		<!-- data-text-label控制开关上面文字 -->
		<div class="make-switch" data-text-label="TV">
			<input type="checkbox">
		</div>
		<!-- data-label-icon控制开关上面的icon -->
		<div class="make-switch switch-small" data-label-icon="fa fa-phone">
			<input type="checkbox">
		</div>
		<!-- data-off-label和data-on-label控制开关上面显示的icon -->
		<div class="make-switch switch-mini" data-on-label="<i class='fa fa-check'></i>" data-off-label="<i class='fa fa-times'></i>" >
			<input type="checkbox">
		</div>
		<!-- disabled控制是否可操作，checked控制开关在哪边 -->
		<div class="make-switch switch-large" data-on="info" data-off="success">
			<input type="checkbox" checked disabled>
		</div>

		<!-- 按下去的按钮btn-group和data-toggle="buttons"控制 -->
		<div class="btn-group" data-toggle="buttons">
			<label class="btn btn-gr-gray btn-sm">
				<input type="checkbox" /> Option 1
			</label>
			<label class="btn btn-gr-gray btn-sm">
				<input type="checkbox" /> Option 2
			</label>
			<label class="btn btn-gr-gray btn-sm">
				<input type="checkbox" /> Option 3
			</label>
		</div>
	</div>

	<div class="row col-lg-12 m-b-30">
		<h2>radio</h2>
		<!-- 多选(checked属性控制控制是否选择,name要一样) -->
		<div class="radio">
			<label>
				<input type="radio" name="radio">
				Option one is this and that&mdash;be sure to include why it's great
			</label>
		</div>
		<div class="radio">
			<label>
				<input type="radio" name="radio">
				Option one is this and that&mdash;be sure to include why it's great
			</label>
		</div>
		<!-- disabled(checked属性控制控制是否选择) -->
		<div class="radio">
			<label>
				<input type="radio" checked disabled>
				This Radio is checked and disabled
			</label>
		</div>

		<!-- 按下去的按钮btn-group和data-toggle="buttons"控制，和checkbox不同的是这个是单选 -->
		<div class="btn-group" data-toggle="buttons">
			<label class="btn btn-gr-gray btn-sm">
				<input type="radio" name="options" id="option1" /> Option 1
			</label>
			<label class="btn btn-gr-gray btn-sm">
				<input type="radio" name="options" id="option2" /> Option 2
			</label>
			<label class="btn btn-gr-gray btn-sm">
				<input type="radio" name="options" id="option3" /> Option 3
			</label>
		</div>
	</div>

	<div class="row col-lg-12 m-b-30">
		<h2>select</h2>
		<!-- 普通的select,select支持input的input-lg三个属性 -->
		<select class="form-control m-b-10">
			<option>Option 1</option>
			<option>Option 2</option>
			<option>Option 3</option>
		</select>
		<!-- 普通的多选 -->
		<select multiple="" class="form-control m-b-10">
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
		</select>
		<!-- disabled -->
		<select class="form-control m-b-20" disabled="">
			<option>Option 1</option>
			<option>Option 2</option>
			<option>Option 3</option>
		</select>

		<!-- 几种特殊的select -->
		<!-- 以下几种class是select,这是一种插件 -->
		<!-- 普通的select,select框子会根据页面的底部上下调用 -->
		<div class="col-md-2 m-b-15">
			<select class="select">
				<option>Default</option>
				<option>Toyota Avalon</option>
				<option>Toyota Crown</option>
				<option>Lexus LX570</option>
				<!-- disabled="disabled"这个属性就无法选择了 -->
				<option disabled="disabled">Toyota Crown</option>
			</select>
		</div>
		<!-- select分组，一个optgroup代表一个分组 -->
		<div class="col-md-2 m-b-15">
			<select class="select">
				<!-- label代表这个分组的头名字 -->
				<optgroup label="Toyota">
					<option>Grouped</option>
					<option>Toyota Avalon</option>
					<option>Toyota Crown</option>
					<option>Toyota FT86 </option>
				</optgroup>
				<optgroup label="Lexus">
					<option>Lexus LS600</option>
					<option>Lexus LFA</option>
					<option>Lexus LX570</option>
				</optgroup>
			</select>
		</div>
		<!-- select分组，利用data-divider分组 -->
		<div class="col-md-2 m-b-15">
			<select class="select">
				<option>Divider</option>
				<option>Toyota Avalon</option>
				<option>Toyota Crown</option>
				<option>Toyota FT86 </option>
				<!-- 分组界限位置(data-divider="true"),这个option不算在元素中 -->
				<option data-divider="true">&nbsp;</option>
				<option>Lexus LS600</option>
				<option>Lexus LFA</option>
				<option>Lexus LX570</option>
			</select>
		</div>
		<!-- 每个option带头标的select -->
		<div class="col-md-2 m-b-15">
			<select class="select">
				<!-- data-icon="fa fa-comment"这个就是头标的属性 -->
				<option data-icon="fa fa-comment">With Icon</option>
				<option data-icon="fa fa-flickr">Toyota FT86</option>
				<option data-icon="fa fa-heart">Toyota Crown</option>
				<option data-icon="fa fa-star">Lexus LX570</option>
			</select>
		</div>
		<!-- select类多选 multiple控制 -->
		<div class="col-md-5 m-b-15">
			<select class="select" multiple>
				<option>Toyota Avalon</option>
				<option>Toyota Crown</option>
				<option>Toyota FT86 </option>
				<option>Lexus LS600</option>
				<option>Lexus LFA</option>
				<option>Lexus LX570</option>
			</select>
		</div>
		<!-- select类多选，当大于多少条的时候，会自动显示数量 data-selected-text-format控制 -->
		<div class="col-md-5 m-b-15">
			<select class="select" multiple data-selected-text-format="count>3">
				<option>Toyota Avalon</option>
				<option>Toyota Crown</option>
				<option>Toyota FT86 </option>
				<option>Lexus LS600</option>
				<option>Lexus LFA</option>
				<option>Lexus LX570</option>
			</select>
		</div>

		<!-- 以下是另外一个choose插件select -->
		<!-- 基本的多选 -->
		<div class="col-md-5 m-b-15">
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
		<div class="col-md-5 m-b-15">
			<!-- 带有上限的select,限制5条，这个是在js中初始化配置的 -->
			<select data-placeholder="Select Users..." class="tag-select-limited" multiple>
				<option value="David Becham">David Becham</option>
				<option value="Christian Bale">Christian Bale</option>
				<option value="Malinda Hollaway">Malinda Hollaway</option>
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
				<option value="Armani Jens" >Armani Jens</option>
				<option value="Wen Diopal">Wen Diopal</option>
				<option value="Aura Moringson">Aura Moringson</option>
				<option value="Peter Robinson">Peter Robinson</option>
				<option value="Dave Watmore">Dave Watmore</option>
				<option value="Jordan Orlendo">Jordan Orlendo</option>
				<option value="Christopher Nolen">Christopher Nolen</option>
			</select>
		</div>
	</div>

	<div class="row col-lg-12 m-b-30">
		<h2>textarea</h2>
		<!-- 超过被覆盖 -->
		<textarea class="form-control overflow m-b-10" rows="3" placeholder="This is a default Textarea..."></textarea>
		<!-- 超过自动扩展 -->
		<textarea class="form-control auto-size m-b-10" placeholder="This is an auto sizing Textarea..."></textarea>
		<!-- disabled -->
		<textarea class="form-control m-b-10" placeholder="This is disabled" disabled></textarea>

		<!-- 富文本框 -->
		<div class="wysiwye-editor"></div>
		<!-- 富文本框2 -->
		<textarea class="markdown-editor m-t-20" name="content" rows="10"></textarea>
	</div>

	<div class="row col-lg-12 m-b-30">
		<h2>button</h2>
		<!-- 按钮大小 -->
		<!-- 按钮常用颜色 黑色btn-alt -->
		<button class="btn btn-lg m-r-5 btn-alt">Large</button>
		<button class="btn m-r-5">Default</button>
		<button class="btn btn-sm m-r-5">Small</button>
		<button class="btn btn-xs m-b-10">Extra Small</button>
		<!-- 按钮块 -->
		<button class="btn btn-block m-b-10">Block Level</button>
		<!-- 按钮禁用 -->
		<button class="btn btn-primary m-b-10" disabled="disabled">Disabled</button>
		<br/>
		<!-- 什么样元素能成为button -->
		<a href="#" class="btn">Link</a>
		<button class="btn">Button</button>
		<input type="submit" value="Submit" class="btn">
		<span class="btn m-b-10">Span</span>
		<br/>
		<!-- 按钮组 -->
		<div class="btn-group m-b-10">
			<button type="button" class="btn-sm btn">Left</button>
			<button type="button" class="btn-sm btn">Middle</button>
			<button type="button" class="btn-sm btn">Right</button>
		</div>
		<br/>
		<!-- 按钮条 按钮组合成的 -->
		<div class="btn-toolbar m-b-10">
			<div class="btn-group">
				<button type="button" class="btn btn-sm">1</button>
				<button type="button" class="btn btn-sm">2</button>
				<button type="button" class="btn btn-sm">3</button>
			</div>
			<div class="btn-group">
				<button type="button" class="btn btn-sm">4</button>
				<button type="button" class="btn btn-sm">5</button>
				<button type="button" class="btn btn-sm">6</button>
			</div>
			<div class="btn-group">
				<button type="button" class="btn btn-sm">7</button>
				<button type="button" class="btn btn-sm">8</button>
				<button type="button" class="btn btn-sm">9</button>
			</div>
		</div>
		<!-- 按钮嵌套 -->
		<div class="btn-group m-b-10">
			<button type="button" class="btn btn-sm btn-alt">1</button>
			<button type="button" class="btn btn-sm btn-alt">2</button>
			<!-- 嵌套元素 -->
			<div class="btn-group m-b-10">
				<button type="button" class="btn btn-sm btn-alt dropdown-toggle" data-toggle="dropdown">
					Dropdown
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Dropdown link</a></li>
					<li><a href="#">Dropdown link</a></li>
				</ul>
			</div>
		</div>
		<br/>
		<!-- 按钮竖着嵌套 -vertical表示竖着分组  -->
		<div class="btn-group-vertical m-r-10">
			<button type="button" class="btn btn-sm">1</button>
			<button type="button" class="btn btn-sm">2</button>
			<!-- 嵌套元素 -->
			<div class="btn-group">
				<button type="button" class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
					Dropdown
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Dropdown link</a></li>
					<li><a href="#">Dropdown link</a></li>
				</ul>
			</div>
			<button type="button" class="btn btn-sm">3</button>
		</div>
		<!-- 按钮块嵌套 -->
		<div class="btn-group btn-group-justified m-b-10 m-t-10">
			<a href="#" class="btn btn-sm">Left</a>
			<a href="#" class="btn btn-sm">Middle</a>
			<a href="#" class="btn btn-sm">Right</a>
		</div>
	</div>

	<div class="row col-lg-12 m-b-30">
		<h2>label</h2>
		<!-- 主要是span控制 类是label label-default 它如果嵌套再h标签中，会随着h标签大小改变而改变 -->
		<h1>Example heading <span class="label label-default">Default</span></h1>
		<h2>Example heading <span class="label label-primary">Primary</span></h2>
		<h3>Example heading <span class="label label-info">Info</span></h3>
		<h2>Example heading <span class="label label-success">Success</span></h2>
		<h5>Example heading <span class="label label-warning">Warning</span></h5>
		<h6>Example heading <span class="label label-danger">Danger</span></h6>
	</div>

	<div class="row col-lg-12 m-b-30">
		<h2>fileupload</h2>
		<div class="fileupload fileupload-new" data-provides="fileupload">
                        <span class="btn btn-file btn-sm btn-alt">
                            <span class="fileupload-new">Select file</span>
                            <span class="fileupload-exists">Change</span>
                            <input type="file" />
                        </span>
			<span class="fileupload-preview"></span>
			<a href="#" class="close close-pic fileupload-exists" data-dismiss="fileupload">
				<i class="fa fa-times"></i>
			</a>
		</div>

		<br/>

		<p>With remove button</p>
		<div class="fileupload fileupload-new row" data-provides="fileupload">
			<div class="input-group col-md-6">
				<div class="uneditable-input form-control">
					<i class="fa fa-file m-r-5 fileupload-exists"></i>
					<span class="fileupload-preview"></span>
				</div>
				<div class="input-group-btn">
                                <span class="btn btn-file btn-alt btn-sm">
                                <span class="fileupload-new">Select file</span>
                                <span class="fileupload-exists">Change</span>
                                <input type="file" />
                            </span>
				</div>

				<a href="#" class="btn btn-sm btn-gr-gray fileupload-exists" data-dismiss="fileupload">Remove</a>
			</div>
		</div>

		<br/>

		<p>Image Preview</p>
		<div class="fileupload fileupload-new" data-provides="fileupload">
			<div class="fileupload-preview thumbnail form-control"></div>

			<div>
                            <span class="btn btn-file btn-alt btn-sm">
                                <span class="fileupload-new">Select image</span>
                                <span class="fileupload-exists">Change</span>
                                <input type="file" />
                            </span>
				<a href="#" class="btn fileupload-exists btn-sm" data-dismiss="fileupload">Remove</a>
			</div>
		</div>

		<br/>

		<p>Another preview option</p>
		<div class="fileupload fileupload-new" data-provides="fileupload">
			<div class="fileupload-new thumbnail small form-control"></div>
			<div class="fileupload-preview form-control fileupload-exists thumbnail small"></div>
			<span class="btn btn-file btn-alt btn-sm">
                            <span class="fileupload-new">Select image</span>
                            <span class="fileupload-exists">Change</span>
                            <input type="file" />
                        </span>
			<a href="#" class="btn-sm btn fileupload-exists" data-dismiss="fileupload">Remove</a>
		</div>
	</div>


	<div class="row col-lg-12 m-b-30">
		<h2>img</h2>
		<!-- 正方形，带圆角 -->
		<img src="${staticPath}img/frame/images-doc/rounded.png" class="img-rounded m-r-10 m-b-10" alt="">
		<!-- 圆形 -->
		<img src="${staticPath}img/frame/images-doc/circle.png" class="img-circle m-r-10 m-b-10" alt="">
		<!-- 正方形 -->
		<img src="${staticPath}img/frame/images-doc/thumbnail.png" class="img-thumbnail m-r-10 m-b-10" alt="">
		<!-- 有阴影的圆形 -->
		<img src="${staticPath}img/frame/images-doc/shadowed.png" class="img-circle img-shadowed m-b-10" alt="">
	</div>

	<!-- 线分隔 -->
	<hr class="whiter"/>
</div>

<!-- **************************************************表格************************************************** -->
<div class="block-area m-b-10">
	<!-- 标题，类似于label -->
	<h3 class="block-title">表格</h3>
	<!-- 普通表格，只有头上有背景 -->
	<table class="table">
		<thead>
		<tr>
			<th>No.</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Username</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>1</td>
			<td>Jhon </td>
			<td>Makinton </td>
			<td>@makinton</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Malinda</td>
			<td>Hollaway</td>
			<td>@hollway</td>
		</tr>
		<tr>
			<td>3</td>
			<td>Wayn</td>
			<td>Parnel</td>
			<td>@wayne123</td>
		</tr>
		</tbody>
	</table>
	<!-- 表格体也带有背景 -->
	<table class="table tile">
		<thead>
		<tr>
			<th>No.</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Username</th>
			<th>Password</th>
			<th>Email</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>1</td>
			<td>Jhon </td>
			<td>Makinton </td>
			<td>@makinton</td>
			<td>123@makinton</td>
			<td>mak@live.com</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Malinda</td>
			<td>Hollaway</td>
			<td>@hollway</td>
			<td>hollaway@mal</td>
			<td>holla@asianet.ch</td>
		</tr>
		<tr>
			<td>3</td>
			<td>Wayn</td>
			<td>Parnel</td>
			<td>@wayne123</td>
			<td>wayn@3rt</td>
			<td>wayne-parn@gmail.com</td>
		</tr>
		</tbody>
	</table>
	<!-- 有边框 -->
	<table class="table table-bordered tile">
		<thead>
		<tr>
			<th>No.</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Username</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>1</td>
			<td>Jhon </td>
			<td>Makinton </td>
			<td>@makinton</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Malinda</td>
			<td>Hollaway</td>
			<td>@hollway</td>
		</tr>
		<tr>
			<td>3</td>
			<td>Wayn</td>
			<td>Parnel</td>
			<td>@wayne123</td>
		</tr>
		</tbody>
	</table>
	<!-- 有悬浮 -->
	<table class="table table-bordered table-hover tile">
		<thead>
		<tr>
			<th>No.</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Username</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>1</td>
			<td>Jhon </td>
			<td>Makinton </td>
			<td>@makinton</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Malinda</td>
			<td>Hollaway</td>
			<td>@hollway</td>
		</tr>
		<tr>
			<td>3</td>
			<td>Wayn</td>
			<td>Parnel</td>
			<td>@wayne123</td>
		</tr>
		</tbody>
	</table>
	<!-- 紧凑些表格 -->
	<table class="table tile table-condensed">
		<thead>
		<tr>
			<th>No.</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Username</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>1</td>
			<td>Jhon </td>
			<td>Makinton </td>
			<td>@makinton</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Malinda</td>
			<td>Hollaway</td>
			<td>@hollway</td>
		</tr>
		<tr>
			<td>3</td>
			<td>Wayn</td>
			<td>Parnel</td>
			<td>@wayne123</td>
		</tr>
		</tbody>
	</table>
	<!-- 有间隔变色的 -->
	<table class="tile table table-bordered table-striped">
		<thead>
		<tr>
			<th>No.</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Username</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>1</td>
			<td>Jhon </td>
			<td>Makinton </td>
			<td>@makinton</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Malinda</td>
			<td>Hollaway</td>
			<td>@hollway</td>
		</tr>
		<tr>
			<td>3</td>
			<td>Wayn</td>
			<td>Parnel</td>
			<td>@wayne123</td>
		</tr>
		<tr>
			<td>4</td>
			<td>TB - Monthly</td>
			<td>05/04/2012</td>
			<td>Call in to confirm</td>
		</tr>
		<tr>
			<td>5</td>
			<td>TB - Monthly</td>
			<td>06/04/2012</td>
			<td>Pending</td>
		</tr>
		</tbody>
	</table>
	<!-- 线分隔 -->
	<hr class="whiter"/>
</div>

<!-- **************************************************字体和文章************************************************** -->
<div class="block-area m-b-10">
	<!-- 标题，类似于label -->
	<h3 class="block-title">字体</h3>
	<!-- article标签默认是文字的包含的容器，没有实际意义，模块化的样式还是block-area -->
	<article class="block-area">
		<!-- 头标签从h1到h6，光的不带任何样式，会自动添加和样式 -->
		<h3>header</h3>
		<!-- 文章段落专门用p标签，不带任何样式 -->
		<p>
			<!-- 文章段落前面醒目的头文字 -->
			<span class="dropcap">D</span>
			Pellentesque lacinia sagittis libero et feugiat. Etiam volutpat adipiscing tortor non luctus. Vivamus venenatis vitae metus et aliquet. Praesent vitae justo purus. In hendrerit lorem nisl, ac lacinia urna aliquet non. Quisque nisi tellus, rhoncus quis est sit amet, lacinia euismod nunc. Aenean nec nibh velit. Fusce quis ante in nisl molestie fringilla. Nunc vitae ante id magna feugiat condimentum. Maecenas sit amet urna massa.
		</p>
		<p>
			<!-- 特殊文字处理，被这个标签括起来后，文字会变得特殊，下面会带有下划线，并且放到上面会有相应提示，提示的内容就是title中填写的文字 -->
			<abbr title="attribute">Integer</abbr>
			<!-- 特殊文字处理，和上述不同的是，这个文字不会变的特殊，只会鼠标放到上面有提示，提示的内容就是title中填写的文字 -->
			<cite title="Source Title">eu </cite>
			<!-- 被包裹的文字会缩小20%，注意这个标签如果单独出来，放在p标签外面，会自动在前面加一个横杠，相当于段落缩进的感觉 -->
			<small>lectus</small>
			sollicitudin, hendrerit est ac, sollicitudin nisl. Quisque viverra sodales lectus nec ultrices. Fusce elit dolor, dignissim a nunc id, varius suscipit turpis. Cras porttitor turpis vitae leo accumsan molestie. Morbi vitae luctus leo. Sed nec scelerisque magna, et adipiscing est. Proin lobortis lectus eu sem ullamcorper, commodo malesuada quam fringilla. Curabitur ac nunc dui. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sagittis enim eu est lacinia, ut egestas ligula imperdiet.
		</p>
		<!-- p标签段落的三种存放样式 -->
		<p class="text-left">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus eget lacus mauris. Ut tempus pellentesque lorem et pulvinar. Aliquam mollis tellus nec nibh ultrices tempor. Duis vehicula arcu a magna venenatis, at interdum nisi dignissim. Curabitur ac nulla.</p>
		<p class="text-center">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus eget lacus mauris. Ut tempus pellentesque lorem et pulvinar. Aliquam mollis tellus nec nibh ultrices tempor. Duis vehicula arcu a magna venenatis, at interdum nisi dignissim. Curabitur ac nulla.</p>
		<p class="text-right">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus eget lacus mauris. Ut tempus pellentesque lorem et pulvinar. Aliquam mollis tellus nec nibh ultrices tempor. Duis vehicula arcu a magna venenatis, at interdum nisi dignissim. Curabitur ac nulla.</p>
		<!-- 地址类段落，用address标签 -->
		<address>
			<!-- 内部只有这个标签特殊,strong会光亮和加粗这个姓名 -->
			<strong>Full Name</strong>
			<!-- 内部所有隔断都有br来隔断 -->
			<br/>
			<a href="mailto:#">first.last@example.com</a>
		</address>
		<!-- 段落缩进，用这个标签，前面会加上一个竖杠 -->
		<blockquote>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante a risus viverra consequat congue a leo. Curabitur ac fermentum enim.</p>
		</blockquote>
		<!-- 段落list列表展示，都是用ol或者ul配合li进行排列的 -->
		<div class="col-sm-6" id="orderedList">
			<!-- 不加type纯数字排列 -->
			<ol>
				<li>Lorem ipsum dolor sit amet</li>
				<li>Consectetur adipiscing elit</li>
				<li>Integer molestie lorem at massa</li>
				<li>Facilisis in pretium nisl aliquet</li>
				<li>Nulla volutpat aliquam velit
				</li><li>Phasellus iaculis neque</li>
				<li>Purus sodales ultricies</li>
				<li>Vestibulum laoreet porttitor sem</li>
				<li>Ac tristique libero volutpat at</li>
				<li>Faucibus porta lacus fringilla vel</li>
				<li>Aenean sit amet erat nunc</li>
				<li>Eget porttitor lorem</li>
			</ol>
		</div>
		<div class="col-sm-6">
			<!-- type为i,按照希腊数字进行排列 -->
			<ol type="i">
				<li>Lorem ipsum dolor sit amet</li>
				<li>Consectetur adipiscing elit</li>
				<li>Integer molestie lorem at massa</li>
				<li>Facilisis in pretium nisl aliquet</li>
				<li>Nulla volutpat aliquam velit</li>
				<li>Phasellus iaculis neque</li>
				<li>Purus sodales ultricies</li>
				<li>Vestibulum laoreet porttitor sem</li>
				<li>Ac tristique libero volutpat at</li>
				<li>Faucibus porta lacus fringilla vel</li>
				<li>Aenean sit amet erat nunc</li>
				<li>Eget porttitor lorem</li>
			</ol>
			<br>
		</div>
		<div class="col-sm-6" id="unorderedList">
			<!-- 没有type头上是点的样式 -->
			<ul>
				<li>Lorem ipsum dolor sit amet</li>
				<li>Consectetur adipiscing elit</li>
				<li>Integer molestie lorem at massa</li>
				<li>Facilisis in pretium nisl aliquet</li>
				<li>Nulla volutpat aliquam velit
					<!-- 可以内部嵌套，嵌套后的内部是空性园的样式 -->
					<ul>
						<li>Phasellus iaculis neque</li>
						<li>Purus sodales ultricies</li>
						<li>Vestibulum laoreet porttitor sem</li>
						<li>Ac tristique libero volutpat at</li>
					</ul>
				</li>
				<li>Faucibus porta lacus fringilla vel</li>
				<li>Aenean sit amet erat nunc</li>
				<li>Eget porttitor lorem</li>
			</ul>
		</div>
		<div class="col-sm-6">
			<!-- 头上是星星的样式 -->
			<ul class="lists-star">
				<li>Lorem ipsum dolor sit amet</li>
				<li>Consectetur adipiscing elit</li>
				<li>Integer molestie lorem at massa</li>
				<li>Facilisis in pretium nisl aliquet</li>
				<li>Nulla volutpat aliquam velit
				</li><li>Phasellus iaculis neque</li>
				<li>Purus sodales ultricies</li>
				<li>Vestibulum laoreet porttitor sem</li>
				<li>Ac tristique libero volutpat at</li>
				<li>Faucibus porta lacus fringilla vel</li>
				<li>Aenean sit amet erat nunc</li>
				<li>Eget porttitor lorem</li>
			</ul>
			<br>
		</div>
		<div class="col-sm-6">
			<!-- 头部是勾的样式 -->
			<ul class="lists-right">
				<li>Lorem ipsum dolor sit amet</li>
				<li>Consectetur adipiscing elit</li>
				<li>Integer molestie lorem at massa</li>
				<li>Facilisis in pretium nisl aliquet</li>
				<li>Nulla volutpat aliquam velit
				</li><li>Phasellus iaculis neque</li>
				<li>Purus sodales ultricies</li>
				<li>Vestibulum laoreet porttitor sem</li>
				<li>Ac tristique libero volutpat at</li>
				<li>Faucibus porta lacus fringilla vel</li>
				<li>Aenean sit amet erat nunc</li>
				<li>Eget porttitor lorem</li>
			</ul>
		</div>
		<div class="col-sm-6">
			<!-- 头部是左箭头的样式 -->
			<ul class="lists-caret">
				<li>Lorem ipsum dolor sit amet</li>
				<li>Consectetur adipiscing elit</li>
				<li>Integer molestie lorem at massa</li>
				<li>Facilisis in pretium nisl aliquet</li>
				<li>Nulla volutpat aliquam velit
				</li><li>Phasellus iaculis neque</li>
				<li>Purus sodales ultricies</li>
				<li>Vestibulum laoreet porttitor sem</li>
				<li>Ac tristique libero volutpat at</li>
				<li>Faucibus porta lacus fringilla vel</li>
				<li>Aenean sit amet erat nunc</li>
				<li>Eget porttitor lorem</li>
			</ul>
		</div>
		<div class="col-sm-6">
			<!-- 头部是空心左箭头的样式 -->
			<ul class="lists-arrow-right">
				<li>Lorem ipsum dolor sit amet</li>
				<li>Consectetur adipiscing elit</li>
				<li>Integer molestie lorem at massa</li>
				<li>Facilisis in pretium nisl aliquet</li>
				<li>Nulla volutpat aliquam velit
				</li><li>Phasellus iaculis neque</li>
				<li>Purus sodales ultricies</li>
				<li>Vestibulum laoreet porttitor sem</li>
				<li>Ac tristique libero volutpat at</li>
				<li>Faucibus porta lacus fringilla vel</li>
				<li>Aenean sit amet erat nunc</li>
				<li>Eget porttitor lorem</li>
			</ul>
		</div>
		<div class="col-sm-6">
			<!-- 头部是书签的样式 -->
			<ul class="lists-mark">
				<li>Lorem ipsum dolor sit amet</li>
				<li>Consectetur adipiscing elit</li>
				<li>Integer molestie lorem at massa</li>
				<li>Facilisis in pretium nisl aliquet</li>
				<li>Nulla volutpat aliquam velit
				</li><li>Phasellus iaculis neque</li>
				<li>Purus sodales ultricies</li>
				<li>Vestibulum laoreet porttitor sem</li>
				<li>Ac tristique libero volutpat at</li>
				<li>Faucibus porta lacus fringilla vel</li>
				<li>Aenean sit amet erat nunc</li>
				<li>Eget porttitor lorem</li>
			</ul>
		</div>


	</article>
	<!-- 线分隔 -->
	<hr class="whiter"/>
</div>

<div class="block-area m-b-10">
	<!-- 标题，类似于label -->
	<h3 class="block-title">媒体(图片、视屏、音频)</h3>
	<p>Single Image (Click to open)</p>
	<a href="${staticPath}img/frame/gallery/1.jpg" data-rel="gallery"  class="pirobox_gall img-popup" title="Lovely evening in Noreway">
		<img src="${staticPath}img/frame/gallery/thumbs/1.jpg" alt="" width="150">
	</a>

	<p class="m-t-20">Image Album (Click to open)</p>
	<a href="${staticPath}img/frame/gallery/1.jpg" data-rel="gallery"  class="pirobox_gall img-popup" title="Fusce sagittis porta porttitor aliquam tincidunt auctor nibh non convallis">
		<img src="${staticPath}img/frame/gallery/thumbs/1.jpg" alt="">
	</a>
	<a href="${staticPath}img/frame/gallery/2.jpg" data-rel="gallery"  class="pirobox_gall img-popup" title="Sed ultrices orci iaculis augue tincidunt gravida">
		<img src="${staticPath}img/frame/gallery/thumbs/2.jpg" alt="">
	</a>
	<a href="${staticPath}img/frame/gallery/3.jpg" data-rel="gallery"  class="pirobox_gall img-popup" title="Donec ullamcorper malesuada magna mollis pellentesque">
		<img src="${staticPath}img/frame/gallery/thumbs/3.jpg" alt="">
	</a>
	<a href="${staticPath}img/frame/gallery/4.jpg" data-rel="gallery"  class="pirobox_gall img-popup" title="Aliquam tincidunt auctor nibh non convallis">
		<img src="${staticPath}img/frame/gallery/thumbs/4.jpg" alt="">
	</a>
	<a href="${staticPath}img/frame/gallery/5.jpg" data-rel="gallery"  class="pirobox_gall img-popup" title="Quelorem venenatis aliquet enim non venenatis">
		<img src="${staticPath}img/frame/gallery/thumbs/5.jpg" alt="">
	</a>

	<p class="m-t-20">Youtube and Vimeo Videos</p>
	<a href="http://youtube.googleapis.com/v/sdUUx5FdySs&amp;hl=en_US" data-rel="iframe-300-300" class="pirobox_gall img-popup" rev="1">                        <i class="icon-expand"></i>
		<img src="${staticPath}img/frame/gallery/thumbs/1.jpg" alt="">
	</a>
	<a href="http://player.vimeo.com/video/54188173" data-rel="iframe-300-300" class="pirobox_gall img-popup" rev="2">
		<img src="${staticPath}img/frame/gallery/thumbs/2.jpg" alt="">
	</a>

	<!-- 轮播图 -->
	<div id="carousel-example-generic" class="carousel slide">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<img src="${staticPath}img/frame/carousel/c-1.jpg" alt="Slide-1">
			</div>
			<div class="item">
				<img src="${staticPath}img/frame/carousel/c-2.jpg" alt="Slide-2">
				<div class="carousel-caption hidden-xs">
					<h3>This is a Caption</h3>
					<p>Sample detail text here</p>
				</div>
			</div>
			<div class="item">
				<img src="${staticPath}img/frame/carousel/c-3.jpg" alt="Slide-3">
				<div class="carousel-caption hidden-xs">
					<h3>This is a Caption</h3>
					<p>Sample detail text here</p>
				</div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
			<i class="icon">&#61903;</i>
		</a>
		<a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
			<i class="icon">&#61815;</i>
		</a>
	</div>

	<!-- 视屏和音频 -->
	<!-- Video -->
	<div class="row">
		<div class="col-md-6 m-b-20">
			<p>Multi-Codec with no JavaScript fallback player - Cross Browser</p>
			<video width="100%" height="100%" id="multiCodec" poster="${staticPath}img/frame/media-player/media-player-poster.jpg" controls="controls" preload="none"> <!-- id could be any according to you -->
				<!-- MP4 source must come first for iOS -->
				<source type="video/mp4" src="${staticPath}media/echohereweare.mp4" />
				<!-- WebM for Firefox 4 and Opera -->
				<source type="video/webm" src="${staticPath}media/echohereweare.webm" />
				<!-- OGG for Firefox 3 -->
				<source type="video/ogg" src="${staticPath}media/echohereweare.ogv" />
				<!-- Fallback flash player for no-HTML5 browsers with JavaScript turned off -->
				<object width="100%" height="100%" type="application/x-shockwave-flash" data="${staticPath}media/flashmediaelement.swf">
					<param name="movie" value="media/flashmediaelement.swf" />
					<param name="flashvars" value="controls=true&amp;poster=../img/frame/media-player/media-player-poster.jpg&amp;file=media/echohereweare.mp4" />
					<!-- Image fall back for non-HTML5 browser with JavaScript turned off and no Flash player installed -->
					<img src="${staticPath}img/frame/media-player/media-player-poster.jpg" width="100%" height="100%" alt="Media" title="No video playback capabilities" />
				</object>
			</video>
		</div>

		<div class="col-md-6 m-b-20">
			<p>Youtube Video (Preview in server NOT local)</p>
			<video id="youtube1" width="100%" height="100%">
				<source src="http://www.youtube.com/watch?v=2CvtOUqd84o" type="video/youtube" >
			</video>
		</div>
	</div>

	<p>Audio Player</p>
	<!-- Audio -->
	<div class="row m-b-20">
		<div class="col-md-7">
			<audio id="audioPlayer" src="media/audio.mp3"></audio>
		</div>
	</div>
	<!-- 线分隔 -->
	<hr class="whiter"/>
</div>

<div class="block-area m-b-10">
	<!-- 标题，类似于label -->
	<h3 class="block-title">列表</h3>
	<!-- 列表 -->
	<div class="listview list-container">
		<header class="listview-header media">
			<input type="checkbox" class="pull-left list-parent-check" value="">

			<ul class="list-inline pull-right m-t-5 m-b-0">
				<li class="pagin-value hidden-xs">35-70</li>
				<li>
					<a href="" title="Previous" class="tooltips">
						<i class="sa-list-back"></i>
					</a>
				</li>
				<li>
					<a href="" title="Next" class="tooltips">
						<i class="sa-list-forwad"></i>
					</a>
				</li>
			</ul>

			<ul class="list-inline list-mass-actions pull-left">
				<li>
					<a data-toggle="modal" href="#compose-message" title="Add" class="tooltips">
						<i class="sa-list-add"></i>
					</a>
				</li>
				<li>
					<a href="" title="Refresh" class="tooltips">
						<i class="sa-list-refresh"></i>
					</a>
				</li>
				<li class="show-on" style="display: none;">
					<a href="" title="Move" class="tooltips">
						<i class="sa-list-move"></i>
					</a>
				</li>
				<li class="show-on" style="display: none;">
					<a href="" title="Delete" class="tooltips">
						<i class="sa-list-delete"></i>
					</a>
				</li>
			</ul>

			<input class="input-sm col-md-4 pull-right message-search" type="text" placeholder="Search....">

			<div class="clearfix"></div>
		</header>

		<div class="media">
			<input type="checkbox" class="pull-left list-check" value="">
			<div class="media-body">
				Per an error perpetua, fierent fastidii recteque ad pro. Mei id brute intellegam
				<div class="list-options">
					<button class="btn btn-sm">View</button>
					<button class="btn btn-sm">Delete</button>
				</div>
			</div>
		</div>

		<div class="media">
			<input type="checkbox" class="pull-left list-check" value="">
			<div class="media-body">
				Nulla vel metus scelerisque ante sollicitudin commodo purus odio
				<div class="clearfix"></div>
				<div class="attrs">Date Created: 09/06/1988</div>
				<div class="block attrs">Members: 78954</div>
				<div class="block attrs">Published: No</div>

				<div class="list-options">
					<button class="btn btn-sm">View</button>
					<button class="btn btn-sm">Delete</button>
				</div>
			</div>
		</div>

		<div class="media">
			<input type="checkbox" class="pull-left list-check" value="">
			<div class="media-body">
				Per an error perpetua, fierent fastidii recteque ad pro. Mei id brute intellegam
				<div class="clearfix"></div>
				<small> An erant explicari salutatus duo, sumo doming delicata in cum. Eos at augue viderer principes</small><br>
				<div class="block attrs">
					Date Created: 25/12/2012
				</div>
				<div class="block attrs">
					Members: 3978
				</div>
				<div class="block attrs">
					Published: Yes
				</div>
			</div>
		</div>

		<div class="media">
			<input type="checkbox" class="pull-left list-check" value="">
			<div class="media-body">
				Inermis patrioque temporibus at ius, eos ei case partem blandit<br>
				<small> An erant explicari salutatus duo, sumo doming delicata in cum. Eos at augue viderer principes</small>
			</div>
			<div class="list-options">
				<button class="btn btn-sm">View</button>
				<button class="btn btn-sm">Delete</button>
			</div>
		</div>

		<div class="media">
			<input type="checkbox" class="pull-left list-check" value="">
			<div class="media-body">
				Eam utamur ceteros ei, per no feugiat saperet omittantur. Qui et erant nonumes abhorreant, eirmod omnium.
				<div class="clearfix"></div>
				<small>Lorem ipsum dolor sit amet, per cu solet docendi</small>
			</div>
			<div class="list-options">
				<button class="btn btn-sm">View</button>
				<button class="btn btn-sm">Delete</button>
			</div>
		</div>

		<div class="media">
			<input type="checkbox" class="pull-left list-check" value="">
			<img class="media-object pull-left" src="../img/frame/profile-pics/1.jpg" alt="" width="45">
			<div class="media-body">
				Nulla vel metus scelerisque ante sollicitudin commodo purus odio.
				<div class="clearfix"></div>
				<small>Lorem ipsum dolor sit amet, per cu solet docendi</small>
			</div>
			<div class="list-options">
				<button class="btn btn-sm">View</button>
				<button class="btn btn-sm">Delete</button>
			</div>
		</div>

		<div class="media">
			<input type="checkbox" class="pull-left list-check" value="">
			<img class="media-object pull-left" src="../img/frame/profile-pics/5.jpg" alt="" width="45">
			<div class="media-body">
				Per an error perpetua, fierent fastidii recteque ad pro. Mei id brute intellegam, te vix ipsum quando facilisis, admodum eleifend quaerendum sed cu. Sale aperiam oportere has an, mea et commodo alterum perpetua fierent fastidii recteque ad pro. Mei id brute intellegam, vim ea invidunt efficiendi. Copiosae definitiones est ex, no oportere urbanitas elaboraret qui, et dicit sadipscing vel.
			</div>
			<div class="list-options">
				<button class="btn btn-sm">View</button>
				<button class="btn btn-sm">Delete</button>
			</div>
		</div>

		<div class="media">
			<input type="checkbox" class="pull-left list-check" value="">
			<img class="media-object pull-left" src="../img/frame/profile-pics/2.jpg" alt="" width="45">
			<div class="media-body">
				Per an error perpetua, fierent fastidii recteque ad pro. Mei id brute intellegam
				<div class="clearfix"></div>
				<small> An erant explicari salutatus duo, sumo doming delicata in cum. Eos at augue viderer principes</small><br>
				<div class="block attrs">
					Date Created: 01/01/2013
				</div>
				<div class="block attrs">
					Members: 17580
				</div>
				<div class="block attrs">
					Published: Yes
				</div>
			</div>
			<div class="list-options">
				<button class="btn btn-sm">View</button>
				<button class="btn btn-sm">Delete</button>
			</div>
		</div>

		<div class="media">
			<input type="checkbox" class="pull-left list-check" value="">
			<div class="media-body">
				Wel ea elit iuvaret. Mea labitur definitiones ex, id atqui accusata vix, vim nibh mandamus ad
				<div class="clearfix"></div>
				<small>Wendy create ipsum dolor sit amet, per cu solet docendi ntoenstion</small>
			</div>
			<div class="list-options">
				<button class="btn btn-sm">View</button>
				<button class="btn btn-sm">Delete</button>
			</div>
		</div>

		<div class="media">
			<input type="checkbox" class="pull-left list-check" value="">
			<div class="media-body">
				Per an error perpetua, fierent fastidii recteque ad pro. Mei id brute intellegam
				<div class="clearfix"></div>
				<small> An erant explicari salutatus duo, sumo doming delicata in cum. Eos at augue viderer principes</small><br>
				<div class="block attrs">
					Date Created: 18/06/2013
				</div>
				<div class="block attrs">
					Members: 610
				</div>
				<div class="block attrs">
					Published: No
				</div>
			</div>
			<div class="list-options">
				<button class="btn btn-sm">View</button>
				<button class="btn btn-sm">Delete</button>
			</div>
		</div>

		<div class="media">
			<input type="checkbox" class="pull-left list-check" value="">
			<div class="media-body">
				Per an error perpetua, fierent fastidii recteque ad pro. Mei id brute intellegam
				<div class="list-options">
					<button class="btn btn-sm">View</button>
					<button class="btn btn-sm">Delete</button>
				</div>
			</div>
		</div>

		<div class="media">
			<input type="checkbox" class="pull-left list-check" value="">
			<div class="media-body">
				Nulla vel metus scelerisque ante sollicitudin commodo purus odio
				<div class="clearfix"></div>
				<div class="attrs">Date Created: 09/06/1988</div>
				<div class="block attrs">Members: 78954</div>
				<div class="block attrs">Published: No</div>

				<div class="list-options">
					<button class="btn btn-sm">View</button>
					<button class="btn btn-sm">Delete</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 线分隔 -->
	<hr class="whiter"/>
</div>

<div class="block-area m-b-10">
	<!-- 标题，类似于label -->
	<h3 class="block-title">插件</h3>
	<!-- 线分隔 -->
	<hr class="whiter"/>
</div>

<div class="block-area m-b-10">
	<!-- 标题，类似于label -->
	<h3 class="block-title">图表</h3>
	<!-- 线分隔 -->
	<hr class="whiter"/>
</div>

<div class="block-area m-b-10">
	<!-- 标题，类似于label -->
	<h3 class="block-title">日历</h3>
	<!-- 线分隔 -->
	<hr class="whiter"/>
</div>
<script>
    $(function(){
        //mask控制input输入格式
        maskForInput();
        //select类初始化
        selectInit();
        //单选和多选框初始化
        checkAndradio();
        //数字加减初始化
        spinnerInit();
        //时间初始化
        timeInit();
        //颜色初始化
        colorInit();
        //普通textarea初始化
        textareaInit();
        //富文本编辑框初始化
        bigTextArae();
        //进度条初始化
        sliderInit();
        //媒体初始化
        //mediaInit();
    })
</script>
