package com.wallet.Idcard.service;

import de.brendamour.jpasskit.PKField;
import de.brendamour.jpasskit.PKPass;
import de.brendamour.jpasskit.enums.PKDataDetectorType;
import de.brendamour.jpasskit.enums.PKPassType;
import de.brendamour.jpasskit.enums.PKTextAlignment;
import de.brendamour.jpasskit.passes.PKGenericPass;
import de.brendamour.jpasskit.signing.PKFileBasedSigningUtil;
import de.brendamour.jpasskit.signing.PKPassTemplateFolder;
import de.brendamour.jpasskit.signing.PKSigningInformation;
import de.brendamour.jpasskit.signing.PKSigningInformationUtil;
import okhttp3.HttpUrl;
import org.springframework.stereotype.Service;


@Service
public class IosWalletService {

    private String appleWWDRCA = "/home/ec2-user/wwdr.pem";
    private String keyStorePath = "/home/ec2-user/Owais_PassKey.p12";
    private String keyStorePassword = "Ib77VT0zNCwC";

    public byte[] getUpdatedPass() {
        try {
            PKSigningInformation pkSigningInformation = new PKSigningInformationUtil()
                    .loadSigningInformationFromPKCS12AndIntermediateCertificate(keyStorePath, keyStorePassword, appleWWDRCA);

            PKPass pass = PKPass.builder()
                    .pass(
                            PKGenericPass.builder()
                                    .passType(PKPassType.PKGenericPass)
                                    .headerFieldBuilder(PKField.builder()
                                                    .key("header0")
                                                    .label("Coverage")
                                                    .value("Medical, Rx, Dental, Vision")
                                                    .textAlignment(PKTextAlignment.PKTextAlignmentLeft))
                                    .primaryFieldBuilder(PKField.builder()
                                            .key("primary")
                                            .label("Enrolee Name")
                                            .value("VVCC"))
                                    .secondaryFieldBuilder(PKField.builder()
                                            .key("secondary0")
                                            .label("Enrollee ID")
                                            .value("123456")
                                            .textAlignment(PKTextAlignment.PKTextAlignmentLeft))
                                    .secondaryFieldBuilder(PKField.builder()
                                            .key("secondary1")
                                            .label("Issued")
                                            .value("123456")
                                            .textAlignment(PKTextAlignment.PKTextAlignmentCenter))
                                    .secondaryFieldBuilder(PKField.builder()
                                            .key("secondary2")
                                            .label("Group")
                                            .value("123456")
                                            .textAlignment(PKTextAlignment.PKTextAlignmentRight))
                                    .auxiliaryFieldBuilder(PKField.builder()
                                            .key("auxiliary1")
                                            .label("RxBin")
                                            .value("1234")
                                            .textAlignment(PKTextAlignment.PKTextAlignmentLeft))
                                    .auxiliaryFieldBuilder(PKField.builder()
                                            .key("auxiliary2")
                                            .label("RxGrp")
                                            .value("1234")
                                            .textAlignment(PKTextAlignment.PKTextAlignmentCenter))
                                    .auxiliaryFieldBuilder(PKField.builder()
                                            .key("auxiliary3")
                                            .label("RxPCN")
                                            .value("1234")
                                            .textAlignment(PKTextAlignment.PKTextAlignmentRight))
                                    .backFieldBuilder(PKField.builder()
                                            .key("phonenumber1")
                                            .label("Medical services")
                                            .value("1-888-288-1718")
                                            .dataDetectorType(PKDataDetectorType.PKDataDetectorTypePhoneNumber))
                                    .backFieldBuilder(PKField.builder()
                                            .key("phonenumber2")
                                            .label("Dental services")
                                            .value("1-888-288-1718")
                                            .dataDetectorType(PKDataDetectorType.PKDataDetectorTypePhoneNumber))
                                    .backFieldBuilder(PKField.builder()
                                            .key("link1")
                                            .label("Web Url")
                                            .value("https://bcbsm.com")
                                            .dataDetectorType(PKDataDetectorType.PKDataDetectorTypeLink))
                                    .backFieldBuilder(PKField.builder()
                                            .key("text1")
                                            .label("Description")
                                            .value("Random Text"))
                    )
                    .formatVersion(1)
                    .passTypeIdentifier("pass.com.bcbsm.mma.idcard")
                    .serialNumber("nmyuxofgna0.5926158093793123")
                    .teamIdentifier("L6PB5H5MH8")
                    .organizationName("BCBSM o=Organizaion 0.38609397483")
                    .description("BCBSM Idcard Wallet Pass")
                    .backgroundColor("rgb(255, 255, 255)")
                    .labelColor("rgb(0,0,0)")
                    .foregroundColor("rgb(0,0,0)")
                    .associatedStoreIdentifier(1098220614L)
                    .webServiceURL(HttpUrl.parse("http://ec2-18-218-213-119.us-east-2.compute.amazonaws.com:8080/").url())
                    .authenticationToken("vxwxd7J8AlNNFPS8k0a0FfUFtq0ewzFdc")
                    .build();
            PKPassTemplateFolder passTemplate = new PKPassTemplateFolder("/home/ec2-user/passimages");
            PKFileBasedSigningUtil pkSigningUtil = new PKFileBasedSigningUtil();
            byte[] signedAndZippedPKPassArchive = pkSigningUtil.createSignedAndZippedPkPassArchive(pass, passTemplate, pkSigningInformation);
            return signedAndZippedPKPassArchive;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed");
        }
        return new byte[0];
    }
}
