#! /bin/bash

# files=( 20120901 20120902 20120903 20120904 20120905 20120906 20120907 20120908 20120909 20120910 20120911 20120912 20120913 20120914 20120915 20120916 20120917 20120918 20120919 20120920 20120921 20120922 20120923 20120924 20120925 20120926 20120927 20120928 20120929 20120930 20121001 20121002 20121003 20121004 20121005 20121006 20121007 20121008 20121009 20121010 20121011 20121012 20121013 20121014 20121015 20121016 20121017 20121018 20121019 )

files=( 20121001 20121002 20121003 20121004 20121005 20121006 20121007 20121008 20121009 20121010 20121011 20121012 20121013 20121014 20121015 20121016 20121017 20121018 20121019 20121020 20121021 20121022 )

for file in ${files[*]}
do
    ./daily.bash $file
done
