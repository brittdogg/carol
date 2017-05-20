drop database if exists carol;
create database carol;

use carol;

create table category(
	categoryId int not null auto_increment,
    categoryName varchar(255) unique not null,
    primary key (categoryId)
);

create table blogUser(
	blogUserId int not null auto_increment,
    displayName varchar(50) not null,
    loginName varchar(50) unique not null,
    userPassword varchar(255) not null,
    enabled boolean default true,
    primary key (blogUserId)
);

create table role(
        roleId int not null auto_increment,
    role varchar (20)not null,
    username varchar (20) not null,
    primary key (roleId)
);


create table blogPost(
	blogPostId int not null auto_increment,
    createdDate datetime not null,
    content text,
    title varchar(255),
    publishDate datetime null,
    expirationDate datetime null,
    modifiedDate datetime not null, 
    approved boolean,
    categoryId int null,
    createdBy int not null,
    modifiedBy int not null,
    urlAlias varchar(255),
    primary key (blogPostId),
    foreign key (createdBy) references blogUser(blogUserId),
    foreign key (modifiedBy) references blogUser(blogUserId),
    foreign key(categoryId) references category(categoryId)
);

create table tag(	
	tagId int not null auto_increment,
    tagName varchar(50),
    primary key (tagId)
    
);


create table staticPage(
	staticPageId int not null auto_increment,
    urlAlias varchar(255) unique not null,
    title varchar(255) null,
    content text null,
    pageOrder int null,
    createdDate datetime not null,
    modifiedDate datetime not null,
    createdBy int not null,
    modifiedBy int not null,
    primary key (staticPageId),
	foreign key (modifiedBy) references blogUser(blogUserId),
    foreign key (createdBy) references blogUser(blogUserId)
);

create table postTag(
	postTagId int not null auto_increment,
	tagId int not null,
    blogPostId int null,
    staticPageId int null,
    primary key (postTagId),
    foreign key(staticPageId) references staticPage(staticPageId),
    foreign key(tagId) references tag(tagId),
    foreign key(blogPostId) references blogPost(blogPostId)
);

create table blogComment(
	commentId int not null auto_increment,
    blogPostId int not null,
    content text,
	createdBy int not null,
    modifiedBy int not null,
    createdDate datetime not null,
    modifiedDate datetime not null,
    foreign key(blogPostId) references blogPost(blogPostId),
    foreign key (createdBy) references blogUser(blogUserId),
    foreign key (modifiedBy) references blogUser(blogUserId),
    primary key (commentId)
);

create table image (
    imageid int not null auto_increment,
    image LONGBLOB not null,
    contenttype varchar(255) not null,
    result varchar(25) not null,
    status varchar(25) not null,
    primary key (imageid)
);