// Description: Java 25 Object interface for CFBam SecSysGrpInc.

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

public interface ICFBamSecSysGrpIncObj
	extends ICFSecSecSysGrpIncObj,
		ICFIntSecSysGrpIncObj
{
	/**
	 *	Initially, the class code for an object is ICFSecSecSysGrpInc.CLASS_CODE, but the Obj layer relies on class code translation to map those
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
	 *	Realise this instance of a SecSysGrpInc.
	 *
	 *	@return	CFSecSecSysGrpIncObj instance which should be subsequently referenced.
	 */
	ICFSecSecSysGrpIncObj realise();

	/**
	 *	Forget this instance from the cache.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 */
	void forget();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecSecSysGrpIncObj the reference to the cached or read (realised) instance.
	 */
	ICFSecSecSysGrpIncObj read();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFSecSecSysGrpIncObj the reference to the cached or read (realised) instance.
	 */
	ICFSecSecSysGrpIncObj read( boolean forceRead );

	/**
	 *	Initialize and return a locked edition of this SecSysGrpInc instance.
	 *
	 *	@return	The newly locked ICFSecSecSysGrpIncEditObj edition of this instance.
	 */
	ICFSecSecSysGrpIncEditObj beginEdit();

	/**
	 *	End this edition of this SecSysGrpInc instance.
	 *
	 *	@throws	CFLibNotSupportedException if you try to end a read-only view.
	 */
	void endEdit();

	/**
	 *	Get the current edition of this SecSysGrpInc instance.
	 *
	 *	@return	The ICFSecSecSysGrpIncEditObj edition of this instance.
	 */
	ICFSecSecSysGrpIncEditObj getEdit();

	/**
	 *	Get the current edition of this SecSysGrpInc instance as a ICFSecSecSysGrpIncEditObj.
	 *
	 *	@return	The ICFSecSecSysGrpIncEditObj edition of this instance.
	 */
	ICFSecSecSysGrpIncEditObj getEditAsSecSysGrpInc();

	/**
	 *	Get the ICFSecSecSysGrpIncTableObj table cache which manages this instance.
	 *
	 *	@return	ICFSecSecSysGrpIncTableObj table cache which manages this instance.
	 */
	ICFSecSecSysGrpIncTableObj getSecSysGrpIncTable();

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
	 *	Get the ICFSecSecSysGrpInc instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecSecSysGrpInc instance which currently backs this object.
	 */
	ICFSecSecSysGrpInc getRec();

	/**
	 *	Internal use only.
	 */
	void setRec( ICFSecSecSysGrpInc value );

	/**
	 *	Get the ICFSecSecSysGrpInc instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFSecSecSysGrpInc instance which currently backs this object.
	 */
	ICFSecSecSysGrpInc getSecSysGrpIncRec();

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
	 *	Get the required ICFBamSecSysGrpObj instance referenced by the Group key.
	 *
	 *	@return	The required ICFBamSecSysGrpObj instance referenced by the Group key.
	 */
	ICFSecSecSysGrpObj getRequiredContainerGroup();

	/**
	 *	Get the required ICFSecSecSysGrpObj instance referenced by the Group key.
	 *
	 *	@return	The required ICFSecSecSysGrpObj instance referenced by the Group key.
	 */
	ICFSecSecSysGrpObj getRequiredContainerGroup( boolean forceRead );

	/**
	 *	Get the required ICFBamSecSysGrpObj instance referenced by the SubGroup key.
	 *
	 *	@return	The required ICFBamSecSysGrpObj instance referenced by the SubGroup key.
	 */
	ICFSecSecSysGrpObj getRequiredParentSubGroup();

	/**
	 *	Get the required ICFSecSecSysGrpObj instance referenced by the SubGroup key.
	 *
	 *	@return	The required ICFSecSecSysGrpObj instance referenced by the SubGroup key.
	 */
	ICFSecSecSysGrpObj getRequiredParentSubGroup( boolean forceRead );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute SecSysGrpId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute SecSysGrpId.
	 */
	CFLibDbKeyHash256 getRequiredSecSysGrpId();

	/**
	 *	Get the required String attribute IncName.
	 *
	 *	@return	The required String attribute IncName.
	 */
	String getRequiredIncName();

	/**
	 *	Internal use only.
	 */
	void copyPKeyToRec();

	/**
	 *	Internal use only.
	 */
	void copyRecToPKey();

}
