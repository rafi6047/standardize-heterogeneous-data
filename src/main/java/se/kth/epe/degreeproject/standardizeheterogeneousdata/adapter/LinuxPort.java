package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

import java.util.HashMap;
import java.util.Map;

public class LinuxPort {

    private static LinuxPort instance = null;
    private static Map<Integer, LinuxService> linuxPortMap;

    private LinuxPort() {
        // Exists only to defeat instantiation.

    }

    public static LinuxPort getInstance() {
        if(instance == null) {
            instance = new LinuxPort();
            instance.setLinuxPortMap();
        }
        return instance;
    }

    public static LinuxService getServiceForPort(Integer port) {

        return linuxPortMap.get(port);


//        return null;
    }

    private void setLinuxPortMap() {
        linuxPortMap = new HashMap<>();
        linuxPortMap.put(1, new LinuxService("tcpmux", "TCP port service multiplexer"));
        linuxPortMap.put(5, new LinuxService("rje", "Remote Job Entry"));
        linuxPortMap.put(7, new LinuxService("echo", "Echo service"));
        linuxPortMap.put(9, new LinuxService("discard", "Null service for connection testing"));
        linuxPortMap.put(11, new LinuxService("systat", "System Status service for listing connected ports"));
        linuxPortMap.put(13, new LinuxService("daytime", "Sends date and time to requesting host"));
        linuxPortMap.put(17, new LinuxService("qotd", "Sends quote of the day to connected host"));
        linuxPortMap.put(18, new LinuxService("msp", "Message Send Protocol"));
        linuxPortMap.put(19, new LinuxService("chargen", "Character Generation service; sends endless stream of characters"));
        linuxPortMap.put(20, new LinuxService("ftp-data", "FTP data port"));
        linuxPortMap.put(21, new LinuxService("ftp", "File Transfer Protocol (FTP) port; sometimes used by File Service Protocol (FSP)"));
        linuxPortMap.put(22, new LinuxService("ssh", "Secure Shell (SSH) service"));
        linuxPortMap.put(23, new LinuxService("telnet", "The Telnet service"));
        linuxPortMap.put(25, new LinuxService("smtp", "Simple Mail Transfer Protocol (SMTP)"));
        linuxPortMap.put(37, new LinuxService("time", "Time Protocol"));
        linuxPortMap.put(39, new LinuxService("rlp", "Resource Location Protocol"));
        linuxPortMap.put(42, new LinuxService("nameserver", "Internet Name Service"));
        linuxPortMap.put(43, new LinuxService("nicname", "WHOIS directory service"));
        linuxPortMap.put(49, new LinuxService("tacacs", "Terminal Access Controller Access Control System for TCP/IP based authentication and access"));
        linuxPortMap.put(50, new LinuxService("re-mail-ck", "Remote Mail Checking Protocol"));
        linuxPortMap.put(53, new LinuxService("domain", "domain name services (such as BIND)"));
        linuxPortMap.put(63, new LinuxService("whois++", "WHOIS++, extended WHOIS services"));
        linuxPortMap.put(67, new LinuxService("bootps", "Bootstrap Protocol (BOOTP) services; also used by Dynamic Host Configuration Protocol (DHCP) services"));
        linuxPortMap.put(68, new LinuxService("bootpc", "Bootstrap (BOOTP) client; also used by Dynamic Host Control Protocol (DHCP) clients"));
        linuxPortMap.put(69, new LinuxService("tftp", "Trivial File Transfer Protocol (TFTP)"));
        linuxPortMap.put(70, new LinuxService("gopher", "Gopher Internet document search and retrieval"));
        linuxPortMap.put(71, new LinuxService("netrjs-1", "Remote Job Service"));
        linuxPortMap.put(72, new LinuxService("netrjs-2", "Remote Job Service"));
        linuxPortMap.put(73, new LinuxService("netrjs-3", "Remote Job Service"));
        linuxPortMap.put(73, new LinuxService("netrjs-4", "Remote Job Service"));
        linuxPortMap.put(79, new LinuxService("finger", "Finger service for user contact information"));
        linuxPortMap.put(80, new LinuxService("http", "HyperText Transfer Protocol (HTTP) for World Wide Web (WWW) services"));
        linuxPortMap.put(88, new LinuxService("kerberos", "Kerberos network authentication system"));
        linuxPortMap.put(95, new LinuxService("supdup", "Telnet protocol extension"));
        linuxPortMap.put(101, new LinuxService("hostname", "Hostname services on SRI-NIC machines"));
        linuxPortMap.put(102, new LinuxService("iso-tsap", "ISO Development Environment (ISODE) network applications"));
        linuxPortMap.put(105, new LinuxService("csnet-ns", "Mailbox nameserver; also used by CSO nameserver"));
        linuxPortMap.put(107, new LinuxService("rtelnet", "Remote Telnet"));
        linuxPortMap.put(109, new LinuxService("pop2", "Post Office Protocol version 2"));
        linuxPortMap.put(110, new LinuxService("pop3", "Post Office Protocol version 3"));
        linuxPortMap.put(111, new LinuxService("sunrpc", "Remote Procedure Call (RPC) Protocol for remote command execution, used by Network Filesystem (NFS)"));
        linuxPortMap.put(113, new LinuxService("auth", "Authentication and Ident protocols"));
        linuxPortMap.put(115, new LinuxService("sftp", "Secure File Transfer Protocol (SFTP) services"));
        linuxPortMap.put(117, new LinuxService("uucp-path", "Unix-to-Unix Copy Protocol (UUCP) Path services"));
        linuxPortMap.put(119, new LinuxService("nntp", "Network News Transfer Protocol (NNTP) for the USENET discussion system"));
        linuxPortMap.put(123, new LinuxService("ntp", "Network Time Protocol (NTP)"));
        linuxPortMap.put(137, new LinuxService("netbios-ns", "NETBIOS Name Service used in Red Hat Enterprise Linux by Samba"));
        linuxPortMap.put(138, new LinuxService("netbios-dgm", "NETBIOS Datagram Service used in Red Hat Enterprise Linux by Samba"));
        linuxPortMap.put(139, new LinuxService("netbios-ssn", "NETBIOS Session Service used in Red Hat Enterprise Linux by Samba"));
        linuxPortMap.put(143, new LinuxService("imap", "Internet Message Access Protocol (IMAP)"));
        linuxPortMap.put(161, new LinuxService("snmp", "Simple Network Management Protocol (SNMP)"));
        linuxPortMap.put(162, new LinuxService("snmptrap", "Traps for SNMP"));
        linuxPortMap.put(163, new LinuxService("cmip-man", "Common Management Information Protocol (CMIP)"));
        linuxPortMap.put(164, new LinuxService("cmip-agent", "Common Management Information Protocol (CMIP)"));
        linuxPortMap.put(174, new LinuxService("mailq", "MAILQ email transport queue"));
        linuxPortMap.put(177, new LinuxService("xdmcp", "X Display Manager Control Protocol (XDMCP)"));
        linuxPortMap.put(178, new LinuxService("nextstep", "NeXTStep window server"));
        linuxPortMap.put(179, new LinuxService("bgp", "Border Gateway Protocol"));
        linuxPortMap.put(191, new LinuxService("prospero", "Prospero distributed filesystem services"));
        linuxPortMap.put(194, new LinuxService("irc", "Internet Relay Chat (IRC)"));
        linuxPortMap.put(199, new LinuxService("smux", "SNMP UNIX Multiplexer"));
        linuxPortMap.put(201, new LinuxService("at-rtmp", "AppleTalk routing"));
        linuxPortMap.put(202, new LinuxService("at-nbp", "AppleTalk name binding"));
        linuxPortMap.put(204, new LinuxService("at-echo", "AppleTalk echo"));
        linuxPortMap.put(206, new LinuxService("at-zis", "AppleTalk zone information"));
        linuxPortMap.put(209, new LinuxService("qmtp", "Quick Mail Transfer Protocol (QMTP)"));
        linuxPortMap.put(210, new LinuxService("z39.50", "NISO Z39.50 database"));
        linuxPortMap.put(213, new LinuxService("ipx", "Internetwork Packet Exchange (IPX), a datagram protocol commonly used in Novell Netware environments"));
        linuxPortMap.put(220, new LinuxService("imap3", "Internet Message Access Protocol version 3"));
        linuxPortMap.put(245, new LinuxService("link", "LINK / 3-DNS iQuery service"));
        linuxPortMap.put(347, new LinuxService("fatserv", "FATMEN file and tape management server"));
        linuxPortMap.put(363, new LinuxService("rsvp_tunnel", "RSVP Tunnel"));
        linuxPortMap.put(369, new LinuxService("rpc2portmap", "Coda file system portmapper"));
        linuxPortMap.put(370, new LinuxService("codaauth2", "Coda file system authentication services"));
        linuxPortMap.put(372, new LinuxService("ulistproc", "UNIX LISTSERV"));
        linuxPortMap.put(389, new LinuxService("ldap", "Lightweight Directory Access Protocol (LDAP)"));
        linuxPortMap.put(427, new LinuxService("svrloc", "Service Location Protocol (SLP)"));
        linuxPortMap.put(434, new LinuxService("mobileip-agent", "Mobile Internet Protocol (IP) agent"));
        linuxPortMap.put(435, new LinuxService("mobilip-mn", "Mobile Internet Protocol (IP) manager"));
        linuxPortMap.put(443, new LinuxService("https", "Secure Hypertext Transfer Protocol (HTTP)"));
        linuxPortMap.put(444, new LinuxService("snpp", "Simple Network Paging Protocol"));
        linuxPortMap.put(445, new LinuxService("microsoft-ds", "Server Message Block (SMB) over TCP/IP"));
        linuxPortMap.put(464, new LinuxService("kpasswd", "Kerberos password and key changing services"));
        linuxPortMap.put(468, new LinuxService("photuris", "Photuris session key management protocol"));
        linuxPortMap.put(487, new LinuxService("saft", "Simple Asynchronous File Transfer (SAFT) protocol"));
        linuxPortMap.put(488, new LinuxService("gss-http", "Generic Security Services (GSS) for HTTP"));
        linuxPortMap.put(496, new LinuxService("pim-rp-disc", "Rendezvous Point Discovery (RP-DISC) for Protocol Independent Multicast (PIM) services"));
        linuxPortMap.put(500, new LinuxService("isakmp", "Internet Security Association and Key Management Protocol (ISAKMP)"));
        linuxPortMap.put(535, new LinuxService("iiop", "Internet Inter-Orb Protocol (IIOP)"));
        linuxPortMap.put(538, new LinuxService("gdomap", "GNUstep Distributed Objects Mapper (GDOMAP)"));
        linuxPortMap.put(546, new LinuxService("dhcpv6-client", "Dynamic Host Configuration Protocol (DHCP) version 6 client"));
        linuxPortMap.put(547, new LinuxService("dhcpv6-server", "Dynamic Host Configuration Protocol (DHCP) version 6 Service"));
        linuxPortMap.put(554, new LinuxService("rtsp", "Real Time Stream Control Protocol (RTSP)"));
        linuxPortMap.put(563, new LinuxService("nntps", "Network News Transport Protocol over Secure Sockets Layer (NNTPS)"));
        linuxPortMap.put(565, new LinuxService("whoami", "whoami user ID listing"));
        linuxPortMap.put(587, new LinuxService("submission", "Mail Message Submission Agent (MSA)"));
        linuxPortMap.put(610, new LinuxService("npmp-local", "Network Peripheral Management Protocol (NPMP) local / Distributed Queueing System (DQS)"));
        linuxPortMap.put(611, new LinuxService("npmp-gui", "Network Peripheral Management Protocol (NPMP) GUI / Distributed Queueing System (DQS)"));
        linuxPortMap.put(612, new LinuxService("hmmp-ind", "HyperMedia Management Protocol (HMMP) Indication / DQS"));
        linuxPortMap.put(631, new LinuxService("ipp", "Internet Printing Protocol (IPP)"));
        linuxPortMap.put(636, new LinuxService("ldaps", "Lightweight Directory Access Protocol over Secure Sockets Layer (LDAPS)"));
        linuxPortMap.put(674, new LinuxService("acap", "Application Configuration Access Protocol (ACAP)"));
        linuxPortMap.put(694, new LinuxService("ha-cluster", "Heartbeat services for High-Availability Clusters"));
        linuxPortMap.put(749, new LinuxService("kerberos-adm", "Kerberos version 5 (v5) 'kadmin' database administration"));
        linuxPortMap.put(750, new LinuxService("kerberos-iv", "Kerberos version 4 (v4) services"));
        linuxPortMap.put(765, new LinuxService("webster", "Network Dictionary"));
        linuxPortMap.put(767, new LinuxService("phonebook", "Network Phonebook"));
        linuxPortMap.put(873, new LinuxService("rsync", "rsync file transfer services"));
        linuxPortMap.put(992, new LinuxService("telnets", "Telnet over Secure Sockets Layer (TelnetS)"));
        linuxPortMap.put(993, new LinuxService("imaps", "Internet Message Access Protocol over Secure Sockets Layer (IMAPS)"));
        linuxPortMap.put(994, new LinuxService("ircs", "Internet Relay Chat over Secure Sockets Layer (IRCS)"));
        linuxPortMap.put(995, new LinuxService("pop3s", "Post Office Protocol version 3 over Secure Sockets Layer (POP3S)"));
    }

    public class LinuxService {
        public String serviceName;
        public String serviceDescription;

        public LinuxService(String serviceName, String serviceDescription) {
            this.serviceName = serviceName;
            this.serviceDescription = serviceDescription;
        }
    }

}

