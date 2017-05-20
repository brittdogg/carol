use carolTest;

-- categories
INSERT INTO `carolTest`.`category` (`categoryId`, `categoryName`) VALUES ('1', 'Blouses');
INSERT INTO `carolTest`.`category` (`categoryId`, `categoryName`) VALUES ('2', 'Pantaloons');

-- users
INSERT INTO `carolTest`.`blogUser` (`blogUserId`, `displayName`, `loginName`, `userPassword`, `enabled`) VALUES ('1', 'Britt Dogg', 'brittdogg', '$2a$10$Vbw6.to5g7VW8fvmAcV6m.ZPjPCKCy58hnPcrOg99HeZEQ8MLXiV.', 1);
INSERT INTO `carolTest`.`blogUser` (`blogUserId`, `displayName`, `loginName`, `userPassword`, `enabled`) VALUES ('2', 'Carol\'s Minion', 'carolsminion', '$2a$10$ddGbMYfmMYBLyDAU/KA9n.rtVQFp30lZGqM93pECMHazi7B.gs/..', 1);
INSERT INTO `carolTest`.`blogUser` (`blogUserId`, `displayName`, `loginName`, `userPassword`, `enabled`) VALUES ('3', 'first name', 'loging name', '$2a$10$fG8pT9oJODRVURLOqlT7suYXlxWee5dydLQqAwZcMDoJwqWorYCSy', 1);
INSERT INTO `carolTest`.`blogUser` (`blogUserId`, `displayName`, `loginName`, `userPassword`, `enabled`) VALUES ('4', 'Lord Vader', 'rando', '$2a$10$dBZC.WXWsNxvvZbstkko7OUR8fuxjzPfekBGBknqUSpixcdbcWqEy', 1);

-- blog posts
INSERT INTO `carolTest`.`blogPost` (`blogPostId`, `createdDate`, `content`, `title`, `publishDate`, `expirationDate`, `modifiedDate`, `approved`, `categoryId`, `createdBy`, `modifiedBy`, `urlAlias`)
	VALUES ('1', '2017-04-04 01:01:01', 'this is content', 'title1', '2017-04-04', '2017-07-07', '2017-04-04 12:30:31', '1', '1', '1', '2', 'first-post');
INSERT INTO `carolTest`.`blogPost` (`blogPostId`, `createdDate`, `content`, `title`, `publishDate`, `expirationDate`, `modifiedDate`, `approved`, `categoryId`, `createdBy`, `modifiedBy`, `urlAlias`)
	VALUES ('2', '2017-04-05 02:02:02', 'more content', 'title2', '2017-05-05 07:18:23', NULL, '2017-04-07 15:15:15', '1', '2', '2', '1', 'second-post');
INSERT INTO `carolTest`.`blogPost` (`blogPostId`, `createdDate`, `content`, `title`, `publishDate`, `expirationDate`, `modifiedDate`, `approved`, `categoryId`, `createdBy`, `modifiedBy`, `urlAlias`)
	VALUES ('3', '2017-04-05 03:03:03', 'this post has the last tag of its kind', 'title3', '2017-05-05 02:02:02', NULL, '2017-04-07 18:13:12', '1', '2', '2', '1', 'third-post');

-- static pages
INSERT INTO `carolTest`.`staticPage` (`staticPageId`, `urlAlias`, `title`, `content`, `pageOrder`, `createdDate`, `modifiedDate`, `createdBy`, `modifiedBy`)
	VALUES ('1', 'test-alias', 'Page Title', 'Blah blah blah', '1', '2017-01-01 01:01:010', '2017-03-03 03:03:03', '1', '1');
INSERT INTO `carolTest`.`staticPage` (`staticPageId`, `urlAlias`, `title`, `content`, `pageOrder`, `createdDate`, `modifiedDate`, `createdBy`, `modifiedBy`)
	VALUES ('2', 'secondpage', 'My Second Static Page', 'Lorem ipsum etc. etc. etc.', '2', '2017-03-17 03:17:10', '2017-03-22 04:23:03', '2', '2');
    
-- tags
INSERT INTO `carolTest`.`tag` (`tagId`, `tagName`) VALUES ('1', 'myNewTag');
INSERT INTO `carolTest`.`tag` (`tagId`, `tagName`) VALUES ('2', 'tagNo2');

-- post-tag relationships: post 1 has no tags, post 2 has tags 1 & 2, post 3 has tag 1 only
INSERT INTO `carolTest`.`postTag` (`postTagId`, `tagId`, `blogPostId`) VALUES ('1', '1', '2');
INSERT INTO `carolTest`.`postTag` (`postTagId`, `tagId`, `blogPostId`) VALUES ('2', '2', '2');
INSERT INTO `carolTest`.`postTag` (`postTagId`, `tagId`, `blogPostId`) VALUES ('3', '1', '3');

-- role table
INSERT INTO `carolTest`.`role` (`roleId`, `username`, `role`) VALUES ('1', 'brittdogg', 'user');
INSERT INTO `carolTest`.`role` (`roleId`, `username`, `role`) VALUES ('2', 'brittdogg', 'admin');
INSERT INTO `carolTest`.`role` (`roleId`, `username`, `role`) VALUES ('3', 'carolsminion', 'user');
INSERT INTO `carolTest`.`role` (`roleId`, `username`, `role`) VALUES ('4', 'carolsminion', 'minion');
INSERT INTO `carolTest`.`role` (`roleId`, `username`, `role`) VALUES ('5', 'rando', 'user');
INSERT INTO `carolTest`.`role` (`roleId`, `username`, `role`) VALUES ('6', 'loging name', 'user');
