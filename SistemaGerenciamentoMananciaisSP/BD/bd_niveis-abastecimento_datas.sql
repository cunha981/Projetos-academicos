select * from niveis
  where data >= date_add(now(), interval -10 day);
  
select date_add(now(), interval -10 day);