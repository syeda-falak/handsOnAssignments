package com.example.currencyconvertion.model;

import com.example.currencyconvertion.utils.RequestResponse;
import com.example.currencyconvertion.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "audit_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuditInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int REQUEST_ID;

    @Column(name = "status")
    Status status;

    @Column(name = "request")
    RequestResponse request;

    @Column(name = "response")
    RequestResponse response;

    @Column(name = "CREATE_TS")
    Date createTs;

    @Column(name = "UPDATE_TS")
    LocalDateTime UPDATE_TS;
}
