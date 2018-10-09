package examples.flowanalysis;

import java.rmi.RemoteException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLInjection {

    /**
     * Some remote method
     * 
     * @param document
     * @param sUserId
     * @param sUserName
     * @param sDataType
     * @param sData
     * @throws RemoteException
     *             - indicates that this method is remote
     */
    void createXMLDocumentAndStoreData(Document document, String sUserId, String sUserName, String sDataType,
            String sData) throws RemoteException {
        // In this example some unverified user data can get into
        // XML storage
        String userAttrName = "User" + sUserId;
        Element user = document.createElement(userAttrName);
        user.setAttribute("name", sUserName);
        Element userData = document.createElement(sDataType);
        userData.setNodeValue(sData);
        user.appendChild(userData);
        // After storing this XML it can be used for
        // internal purposes, even passed as a data source to some critical
        // processing
        // method.
        // It can also be traversed using JXpath or Digester which can
        // create malicious objects that would cause dangerous code to be
        // executed .
    }
}
