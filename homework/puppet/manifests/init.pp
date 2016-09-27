exec { 'apt-get update':
  path => '/usr/bin',
}

package { 'build-essential':
  ensure => present,
  require => Exec['apt-get update'],
}

package { 'openjdk-7-jdk':
  ensure => present,
  require => Exec['apt-get update'],
}


package { 'gradle':
  ensure => present,
  require => Exec['apt-get update'],
}
