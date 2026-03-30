// Description: Java 25 Object interface for CFBam SecUserEMConf.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

public interface ICFBamSecUserEMConfObj
	extends ICFSecSecUserEMConfObj,
		ICFIntSecUserEMConfObj
{
	/**
	 *	Initially, the class code for an object is ICFSecSecUserEMConf.CLASS_CODE, but the Obj layer relies on class code translation to map those
	 *	backing store entities to a runtime set of front-facing classcodes that the clients download and use when talking to the server implementing this code base.
	 *
	 *	@return The runtime class code used by this object. Only after the system is fully booted are these values stable and reliable.
	 */
	int getClassCode();
	/**
	 *	Get the user who created this instance.
	 *
	 *	@return	The ICFSecSecUserObj instance who created this instance.
	 */
	ICFSecSecUserObj getCreatedBy();

	/**
	 *	Get the LocalDateTime this instance was created.
	 *
	 *	@return	The LocalDateTime value for the creation time of the instance.
	 */
	LocalDateTime getCreatedAt();

	/**
	 *	Get the user who updated this instance.
	 *
	 *	@return	The ICFSecSecUserObj instance who updated this instance.
	 */
	ICFSecSecUserObj getUpdatedBy();

	/**
	 *	Get the LocalDateTime date-time this instance was updated.
	 *
	 *	@return	The LocalDateTime value for the create time of the instance.
	 */
	LocalDateTime getUpdatedAt();
	/**
	 *	Realise this instance of a SecUserEMConf.
	 *
	 *	@return	CFSecSecUserEMConfObj instance which should be subsequently referenced.
	 */
	ICFSecSecUserEMConfObj realise();

	/**
	 *	Forget this instance from the cache.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 */
	void forget();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecSecUserEMConfObj the reference to the cached or read (realised) instance.
	 */
	ICFSecSecUserEMConfObj read();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecSecUserEMConfObj the reference to the cached or read (realised) instance.
	 */
	ICFSecSecUserEMConfObj read( boolean forceRead );

	/**
	 *	Initialize and return a locked edition of this SecUserEMConf instance.
	 *
	 *	@return	The newly locked ICFSecSecUserEMConfEditObj edition of this instance.
	 */
	ICFSecSecUserEMConfEditObj beginEdit();

	/**
	 *	End this edition of this SecUserEMConf instance.
	 *
	 *	@throws	CFLibNotSupportedException if you try to end a read-only view.
	 */
	void endEdit();

	/**
	 *	Get the current edition of this SecUserEMConf instance.
	 *
	 *	@return	The ICFSecSecUserEMConfEditObj edition of this instance.
	 */
	ICFSecSecUserEMConfEditObj getEdit();

	/**
	 *	Get the current edition of this SecUserEMConf instance as a ICFSecSecUserEMConfEditObj.
	 *
	 *	@return	The ICFSecSecUserEMConfEditObj edition of this instance.
	 */
	ICFSecSecUserEMConfEditObj getEditAsSecUserEMConf();

	/**
	 *	Get the ICFSecSecUserEMConfTableObj table cache which manages this instance.
	 *
	 *	@return	ICFSecSecUserEMConfTableObj table cache which manages this instance.
	 */
	ICFSecSecUserEMConfTableObj getSecUserEMConfTable();

	/**
	 *	Get the ICFSecSchemaObj schema cache which manages this instance.
	 *
	 *	@return	ICFSecSchemaObj schema cache which manages this instance.
	 */
	ICFSecSchemaObj getSchema();

	/**
	 *	Set the ICFSecSchemaObj schema cache which manages this instance.
	 *	Should only be used to install overloads of the buff implementation wired specifically to a transport implementation
	 *	that eventually hits a server running a JPA backend.
	 *
	 *	@param schema	ICFSecSchemaObj schema cache which manages this instance.
	 */
	void setSchema(ICFSecSchemaObj schema);

	/**
	 *	Get the ICFSecSecUserEMConf instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecSecUserEMConf instance which currently backs this object.
	 */
	ICFSecSecUserEMConf getRec();

	/**
	 *	Internal use only.
	 */
	void setRec( ICFSecSecUserEMConf value );

	/**
	 *	Get the ICFSecSecUserEMConf instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecSecUserEMConf instance which currently backs this object.
	 */
	ICFSecSecUserEMConf getSecUserEMConfRec();

	/**
	 *	Is this a new instance?
	 *
	 *	@return	True if this is a new instance, otherwise false if it has
	 *		been read, locked, or created.
	 */
	boolean getIsNew();

	/**
	 *	Indicate whether this is a new instance.
	 *	<p>
	 *	This method should only be used by implementation internals.
	 *
	 *	@param	True if this is a new instance, otherwise false.
	 */
	void setIsNew( boolean value );

	/**
	 *	Get the required ICFBamSecUserObj instance referenced by the User key.
	 *
	 *	@return	The required ICFBamSecUserObj instance referenced by the User key.
	 */
	ICFSecSecUserObj getRequiredContainerUser();

	/**
	 *	Get the required ICFSecSecUserObj instance referenced by the User key.
	 *
	 *	@return	The required ICFSecSecUserObj instance referenced by the User key.
	 */
	ICFSecSecUserObj getRequiredContainerUser( boolean forceRead );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute SecUserId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute SecUserId.
	 */
	CFLibDbKeyHash256 getRequiredSecUserId();

	/**
	 *	Get the required String attribute ConfirmEMailAddr.
	 *
	 *	@return	The required String attribute ConfirmEMailAddr.
	 */
	String getRequiredConfirmEMailAddr();

	/**
	 *	Get the required LocalDateTime attribute EMailSentStamp.
	 *
	 *	@return	The required LocalDateTime attribute EMailSentStamp.
	 */
	LocalDateTime getRequiredEMailSentStamp();

	/**
	 *	Get the required CFLibUuid6 attribute EMConfirmationUuid6.
	 *
	 *	@return	The required CFLibUuid6 attribute EMConfirmationUuid6.
	 */
	CFLibUuid6 getRequiredEMConfirmationUuid6();

	/**
	 *	Get the required boolean attribute NewAccount.
	 *
	 *	@return	The required boolean attribute NewAccount.
	 */
	boolean getRequiredNewAccount();

	/**
	 *	Internal use only.
	 */
	void copyPKeyToRec();

	/**
	 *	Internal use only.
	 */
	void copyRecToPKey();

}
