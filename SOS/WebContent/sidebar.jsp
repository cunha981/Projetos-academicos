		<aside class="fixed skin-6">	
			<div class="sidebar-inner scrollable-sidebars">	
				<div class="user-block clearfix">
					<img src="resources/img/user.png" alt="User Avatar">
				</div><!-- /user-block -->
				<div class="main-menu">
					<ul>
						<li>
							<a href="index.jsp">
								<span class="menu-icon">
									<i class="fa fa-desktop fa-lg"></i> 
								</span>
								<span class="text">
									Dashboard
								</span>
								<span class="menu-hover"></span>
							</a>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa fa-handshake-o fa-lg"></i> 
								</span>
								<span class="text">
									Organização Social
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="os-inserir.jsp"><span class="submenu-label">Cadastrar OS</span></a></li>
								<!--  <li class="active"> -->
								<li><a href="os-consultar.jsp"><span class="submenu-label">Consultar OS</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-briefcase fa-lg"></i> 
								</span>
								<span class="text">
									Gerenciar projetos
								</span>
								<!-- <span class="badge badge-success bounceIn animation-delay5">9</span> -->
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="VoluntarioController.do?acao=associados"><span class="submenu-label">Voluntários associados</span></a></li>
								<!--  <li class="active"> -->
								<li><a href="VoluntarioController.do?acao=compativel"><span class="submenu-label">Voluntários pendentes</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa fa-user fa-lg"></i> 
								</span>
								<span class="text">
									Voluntário
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="vol-inserir.jsp"><span class="submenu-label">Cadastrar Voluntário</span></a></li>
								<!--  <li class="active"> -->
								<li><a href="vol-consultar.jsp"><span class="submenu-label">Consultar Voluntário</span></a></li>
							</ul>
						</li>
					</ul>
				</div><!-- /main-menu -->
			</div><!-- /sidebar-inner -->
		</aside>