#!/bin/bash

datum=`/bin/date +%Y%m%d-%H`

#/usr/bin/mysqladmin --user=root --password=dinky01 stop-slave

/usr/bin/mysqldump --user=root --password=dinky01 --lock-all-tables \
      --all-databases > /media/usb/sqlbackup/backup-${datum}.sql

#/usr/bin/mysqladmin --user=root --password=dinky01 start-slave

for file in "$( /usr/bin/find /media/usb/sqlbackup -type f -mtime +2 )"
do
  /bin/rm -f $file
done

exit 0
