// Description: Java 25 Table Object implementation for PopSubDep3.

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
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamPopSubDep3TableObj
	implements ICFBamPopSubDep3TableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamPopSubDep3.CLASS_CODE;
	protected static final int backingClassCode = ICFBamPopSubDep3.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> members;
	private Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> allPopSubDep3;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > > indexByTenantIdx;
	private Map< ICFBamPopDepByRelationIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > > indexByRelationIdx;
	private Map< ICFBamPopDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > > indexByDefSchemaIdx;
	private Map< ICFBamPopSubDep3ByPopSubDep2IdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > > indexByPopSubDep2Idx;
	private Map< ICFBamPopSubDep3ByUNameIdxKey,
		ICFBamPopSubDep3Obj > indexByUNameIdx;
	public static String TABLE_NAME = "PopSubDep3";
	public static String TABLE_DBNAME = "popsubdep3";

	public CFBamPopSubDep3TableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamPopSubDep3Obj>();
		allPopSubDep3 = null;
		indexByTenantIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		indexByPopSubDep2Idx = null;
		indexByUNameIdx = null;
	}

	public CFBamPopSubDep3TableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamPopSubDep3Obj>();
		allPopSubDep3 = null;
		indexByTenantIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		indexByPopSubDep2Idx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamPopSubDep3TableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamPopSubDep3TableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allPopSubDep3 = null;
		indexByTenantIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		indexByPopSubDep2Idx = null;
		indexByUNameIdx = null;
		List<ICFBamPopSubDep3Obj> toForget = new LinkedList<ICFBamPopSubDep3Obj>();
		ICFBamPopSubDep3Obj cur = null;
		Iterator<ICFBamPopSubDep3Obj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamPopSubDep3Obj.
	 */
	@Override
	public ICFBamPopSubDep3Obj newInstance() {
		ICFBamPopSubDep3Obj inst = new CFBamPopSubDep3Obj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamPopSubDep3Obj.
	 */
	@Override
	public ICFBamPopSubDep3EditObj newEditInstance( ICFBamPopSubDep3Obj orig ) {
		ICFBamPopSubDep3EditObj edit = new CFBamPopSubDep3EditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamPopSubDep3Obj realisePopSubDep3( ICFBamPopSubDep3Obj Obj ) {
		ICFBamPopSubDep3Obj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamPopSubDep3Obj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamPopSubDep3Obj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByRelationIdx != null ) {
				ICFBamPopDepByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					indexByRelationIdx.remove( keyRelationIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByPopSubDep2Idx != null ) {
				ICFBamPopSubDep3ByPopSubDep2IdxKey keyPopSubDep2Idx =
					schema.getCFBamBackingStore().getFactoryPopSubDep3().newByPopSubDep2IdxKey();
				keyPopSubDep2Idx.setRequiredPopSubDep2Id( keepObj.getRequiredPopSubDep2Id() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > mapPopSubDep2Idx = indexByPopSubDep2Idx.get( keyPopSubDep2Idx );
				if( mapPopSubDep2Idx != null ) {
					mapPopSubDep2Idx.remove( keepObj.getPKey() );
					if( mapPopSubDep2Idx.size() <= 0 ) {
						indexByPopSubDep2Idx.remove( keyPopSubDep2Idx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamPopSubDep3ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryPopSubDep3().newByUNameIdxKey();
				keyUNameIdx.setRequiredPopSubDep2Id( keepObj.getRequiredPopSubDep2Id() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamPopSubDep3Obj)schema.getPopDepTableObj().realisePopDep( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRelationIdx != null ) {
				ICFBamPopDepByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					mapRelationIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPopSubDep2Idx != null ) {
				ICFBamPopSubDep3ByPopSubDep2IdxKey keyPopSubDep2Idx =
					schema.getCFBamBackingStore().getFactoryPopSubDep3().newByPopSubDep2IdxKey();
				keyPopSubDep2Idx.setRequiredPopSubDep2Id( keepObj.getRequiredPopSubDep2Id() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > mapPopSubDep2Idx = indexByPopSubDep2Idx.get( keyPopSubDep2Idx );
				if( mapPopSubDep2Idx != null ) {
					mapPopSubDep2Idx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamPopSubDep3ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryPopSubDep3().newByUNameIdxKey();
				keyUNameIdx.setRequiredPopSubDep2Id( keepObj.getRequiredPopSubDep2Id() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allPopSubDep3 != null ) {
				allPopSubDep3.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamPopSubDep3Obj)schema.getPopDepTableObj().realisePopDep( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allPopSubDep3 != null ) {
				allPopSubDep3.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRelationIdx != null ) {
				ICFBamPopDepByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					mapRelationIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPopSubDep2Idx != null ) {
				ICFBamPopSubDep3ByPopSubDep2IdxKey keyPopSubDep2Idx =
					schema.getCFBamBackingStore().getFactoryPopSubDep3().newByPopSubDep2IdxKey();
				keyPopSubDep2Idx.setRequiredPopSubDep2Id( keepObj.getRequiredPopSubDep2Id() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj > mapPopSubDep2Idx = indexByPopSubDep2Idx.get( keyPopSubDep2Idx );
				if( mapPopSubDep2Idx != null ) {
					mapPopSubDep2Idx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamPopSubDep3ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryPopSubDep3().newByUNameIdxKey();
				keyUNameIdx.setRequiredPopSubDep2Id( keepObj.getRequiredPopSubDep2Id() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamPopSubDep3Obj createPopSubDep3( ICFBamPopSubDep3Obj Obj ) {
		ICFBamPopSubDep3Obj obj = Obj;
		ICFBamPopSubDep3 rec = obj.getPopSubDep3Rec();
		schema.getCFBamBackingStore().getTablePopSubDep3().createPopSubDep3(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamPopSubDep3Obj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamPopSubDep3Obj readPopSubDep3( CFLibDbKeyHash256 pkey ) {
		return( readPopSubDep3( pkey, false ) );
	}

	@Override
	public ICFBamPopSubDep3Obj readPopSubDep3( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamPopSubDep3Obj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamPopSubDep3 readRec = schema.getCFBamBackingStore().getTablePopSubDep3().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamPopSubDep3Obj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamPopSubDep3Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamPopSubDep3Obj readCachedPopSubDep3( CFLibDbKeyHash256 pkey ) {
		ICFBamPopSubDep3Obj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposePopSubDep3( ICFBamPopSubDep3Obj obj )
	{
		final String S_ProcName = "CFBamPopSubDep3TableObj.reallyDeepDisposePopSubDep3() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamPopSubDep3Obj existing = readCachedPopSubDep3( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamPopSubDep3ByPopSubDep2IdxKey keyPopSubDep2Idx = schema.getCFBamBackingStore().getFactoryPopSubDep3().newByPopSubDep2IdxKey();
		keyPopSubDep2Idx.setRequiredPopSubDep2Id( existing.getRequiredPopSubDep2Id() );

		ICFBamPopSubDep3ByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryPopSubDep3().newByUNameIdxKey();
		keyUNameIdx.setRequiredPopSubDep2Id( existing.getRequiredPopSubDep2Id() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );



		if( indexByPopSubDep2Idx != null ) {
			if( indexByPopSubDep2Idx.containsKey( keyPopSubDep2Idx ) ) {
				indexByPopSubDep2Idx.get( keyPopSubDep2Idx ).remove( pkey );
				if( indexByPopSubDep2Idx.get( keyPopSubDep2Idx ).size() <= 0 ) {
					indexByPopSubDep2Idx.remove( keyPopSubDep2Idx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


		schema.getPopDepTableObj().reallyDeepDisposePopDep( obj );
	}
	@Override
	public void deepDisposePopSubDep3( CFLibDbKeyHash256 pkey ) {
		ICFBamPopSubDep3Obj obj = readCachedPopSubDep3( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamPopSubDep3Obj lockPopSubDep3( CFLibDbKeyHash256 pkey ) {
		ICFBamPopSubDep3Obj locked = null;
		ICFBamPopSubDep3 lockRec = schema.getCFBamBackingStore().getTablePopSubDep3().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamPopSubDep3Obj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamPopSubDep3Obj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockPopSubDep3", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readAllPopSubDep3() {
		return( readAllPopSubDep3( false ) );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readAllPopSubDep3( boolean forceRead ) {
		final String S_ProcName = "readAllPopSubDep3";
		if( ( allPopSubDep3 == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> map = new HashMap<CFLibDbKeyHash256,ICFBamPopSubDep3Obj>();
			allPopSubDep3 = map;
			ICFBamPopSubDep3[] recList = schema.getCFBamBackingStore().getTablePopSubDep3().readAllDerived( null );
			ICFBamPopSubDep3 rec;
			ICFBamPopSubDep3Obj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopSubDep3Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopSubDep3Obj realised = (ICFBamPopSubDep3Obj)obj.realise();
			}
		}
		int len = allPopSubDep3.size();
		ICFBamPopSubDep3Obj arr[] = new ICFBamPopSubDep3Obj[len];
		Iterator<ICFBamPopSubDep3Obj> valIter = allPopSubDep3.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamPopSubDep3Obj> arrayList = new ArrayList<ICFBamPopSubDep3Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopSubDep3Obj> cmp = new Comparator<ICFBamPopSubDep3Obj>() {
			@Override
			public int compare( ICFBamPopSubDep3Obj lhs, ICFBamPopSubDep3Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamPopSubDep3Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readCachedAllPopSubDep3() {
		final String S_ProcName = "readCachedAllPopSubDep3";
		ArrayList<ICFBamPopSubDep3Obj> arrayList = new ArrayList<ICFBamPopSubDep3Obj>();
		if( allPopSubDep3 != null ) {
			int len = allPopSubDep3.size();
			ICFBamPopSubDep3Obj arr[] = new ICFBamPopSubDep3Obj[len];
			Iterator<ICFBamPopSubDep3Obj> valIter = allPopSubDep3.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamPopSubDep3Obj> cmp = new Comparator<ICFBamPopSubDep3Obj>() {
			public int compare( ICFBamPopSubDep3Obj lhs, ICFBamPopSubDep3Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamPopSubDep3Obj readPopSubDep3ByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readPopSubDep3ByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamPopSubDep3Obj readPopSubDep3ByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamPopSubDep3Obj obj = readPopSubDep3( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readPopSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readPopSubDep3ByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readPopSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopSubDep3ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep3Obj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopSubDep3Obj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopSubDep3Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopSubDep3Obj realised = (ICFBamPopSubDep3Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopSubDep3Obj arr[] = new ICFBamPopSubDep3Obj[len];
		Iterator<ICFBamPopSubDep3Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamPopSubDep3Obj> arrayList = new ArrayList<ICFBamPopSubDep3Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopSubDep3Obj> cmp = new Comparator<ICFBamPopSubDep3Obj>() {
			@Override
			public int compare( ICFBamPopSubDep3Obj lhs, ICFBamPopSubDep3Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamPopSubDep3Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readPopSubDep3ByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readPopSubDep3ByRelationIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readPopSubDep3ByRelationIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopSubDep3ByRelationIdx";
		ICFBamPopDepByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> dict;
		if( indexByRelationIdx == null ) {
			indexByRelationIdx = new HashMap< ICFBamPopDepByRelationIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep3Obj > >();
		}
		if( ( ! forceRead ) && indexByRelationIdx.containsKey( key ) ) {
			dict = indexByRelationIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopSubDep3Obj>();
			ICFBamPopDepObj obj;
			ICFBamPopDep[] recList = schema.getCFBamBackingStore().getTablePopDep().readDerivedByRelationIdx( null,
				RelationId );
			ICFBamPopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopSubDep3Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopSubDep3Obj realised = (ICFBamPopSubDep3Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRelationIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopSubDep3Obj arr[] = new ICFBamPopSubDep3Obj[len];
		Iterator<ICFBamPopSubDep3Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamPopSubDep3Obj> arrayList = new ArrayList<ICFBamPopSubDep3Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopSubDep3Obj> cmp = new Comparator<ICFBamPopSubDep3Obj>() {
			@Override
			public int compare( ICFBamPopSubDep3Obj lhs, ICFBamPopSubDep3Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamPopSubDep3Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readPopSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readPopSubDep3ByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readPopSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopSubDep3ByDefSchemaIdx";
		ICFBamPopDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamPopDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep3Obj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopSubDep3Obj>();
			ICFBamPopDepObj obj;
			ICFBamPopDep[] recList = schema.getCFBamBackingStore().getTablePopDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamPopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopSubDep3Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopSubDep3Obj realised = (ICFBamPopSubDep3Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopSubDep3Obj arr[] = new ICFBamPopSubDep3Obj[len];
		Iterator<ICFBamPopSubDep3Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamPopSubDep3Obj> arrayList = new ArrayList<ICFBamPopSubDep3Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopSubDep3Obj> cmp = new Comparator<ICFBamPopSubDep3Obj>() {
			@Override
			public int compare( ICFBamPopSubDep3Obj lhs, ICFBamPopSubDep3Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamPopSubDep3Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readPopSubDep3ByPopSubDep2Idx( CFLibDbKeyHash256 PopSubDep2Id )
	{
		return( readPopSubDep3ByPopSubDep2Idx( PopSubDep2Id,
			false ) );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readPopSubDep3ByPopSubDep2Idx( CFLibDbKeyHash256 PopSubDep2Id,
		boolean forceRead )
	{
		final String S_ProcName = "readPopSubDep3ByPopSubDep2Idx";
		ICFBamPopSubDep3ByPopSubDep2IdxKey key = schema.getCFBamBackingStore().getFactoryPopSubDep3().newByPopSubDep2IdxKey();
		key.setRequiredPopSubDep2Id( PopSubDep2Id );
		Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> dict;
		if( indexByPopSubDep2Idx == null ) {
			indexByPopSubDep2Idx = new HashMap< ICFBamPopSubDep3ByPopSubDep2IdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep3Obj > >();
		}
		if( ( ! forceRead ) && indexByPopSubDep2Idx.containsKey( key ) ) {
			dict = indexByPopSubDep2Idx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopSubDep3Obj>();
			ICFBamPopSubDep3Obj obj;
			ICFBamPopSubDep3[] recList = schema.getCFBamBackingStore().getTablePopSubDep3().readDerivedByPopSubDep2Idx( null,
				PopSubDep2Id );
			ICFBamPopSubDep3 rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopSubDep3Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopSubDep3Obj realised = (ICFBamPopSubDep3Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPopSubDep2Idx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopSubDep3Obj arr[] = new ICFBamPopSubDep3Obj[len];
		Iterator<ICFBamPopSubDep3Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamPopSubDep3Obj> arrayList = new ArrayList<ICFBamPopSubDep3Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopSubDep3Obj> cmp = new Comparator<ICFBamPopSubDep3Obj>() {
			@Override
			public int compare( ICFBamPopSubDep3Obj lhs, ICFBamPopSubDep3Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamPopSubDep3Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamPopSubDep3Obj readPopSubDep3ByUNameIdx( CFLibDbKeyHash256 PopSubDep2Id,
		String Name )
	{
		return( readPopSubDep3ByUNameIdx( PopSubDep2Id,
			Name,
			false ) );
	}

	@Override
	public ICFBamPopSubDep3Obj readPopSubDep3ByUNameIdx( CFLibDbKeyHash256 PopSubDep2Id,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamPopSubDep3ByUNameIdxKey,
				ICFBamPopSubDep3Obj >();
		}
		ICFBamPopSubDep3ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryPopSubDep3().newByUNameIdxKey();
		key.setRequiredPopSubDep2Id( PopSubDep2Id );
		key.setRequiredName( Name );
		ICFBamPopSubDep3Obj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamPopSubDep3 rec = schema.getCFBamBackingStore().getTablePopSubDep3().readDerivedByUNameIdx( null,
				PopSubDep2Id,
				Name );
			if( rec != null ) {
				obj = (ICFBamPopSubDep3Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamPopSubDep3Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamPopSubDep3Obj readCachedPopSubDep3ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamPopSubDep3Obj obj = null;
		obj = readCachedPopSubDep3( Id );
		return( obj );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readCachedPopSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedPopSubDep3ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamPopSubDep3Obj> arrayList = new ArrayList<ICFBamPopSubDep3Obj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamPopSubDep3Obj arr[] = new ICFBamPopSubDep3Obj[len];
				Iterator<ICFBamPopSubDep3Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamPopSubDep3Obj obj;
			Iterator<ICFBamPopSubDep3Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopSubDep3Obj> cmp = new Comparator<ICFBamPopSubDep3Obj>() {
			@Override
			public int compare( ICFBamPopSubDep3Obj lhs, ICFBamPopSubDep3Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readCachedPopSubDep3ByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedPopSubDep3ByRelationIdx";
		ICFBamPopDepByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamPopSubDep3Obj> arrayList = new ArrayList<ICFBamPopSubDep3Obj>();
		if( indexByRelationIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> dict;
			if( indexByRelationIdx.containsKey( key ) ) {
				dict = indexByRelationIdx.get( key );
				int len = dict.size();
				ICFBamPopSubDep3Obj arr[] = new ICFBamPopSubDep3Obj[len];
				Iterator<ICFBamPopSubDep3Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamPopSubDep3Obj obj;
			Iterator<ICFBamPopSubDep3Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopSubDep3Obj> cmp = new Comparator<ICFBamPopSubDep3Obj>() {
			@Override
			public int compare( ICFBamPopSubDep3Obj lhs, ICFBamPopSubDep3Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readCachedPopSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedPopSubDep3ByDefSchemaIdx";
		ICFBamPopDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamPopSubDep3Obj> arrayList = new ArrayList<ICFBamPopSubDep3Obj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamPopSubDep3Obj arr[] = new ICFBamPopSubDep3Obj[len];
				Iterator<ICFBamPopSubDep3Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamPopSubDep3Obj obj;
			Iterator<ICFBamPopSubDep3Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopSubDep3Obj> cmp = new Comparator<ICFBamPopSubDep3Obj>() {
			@Override
			public int compare( ICFBamPopSubDep3Obj lhs, ICFBamPopSubDep3Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamPopSubDep3Obj> readCachedPopSubDep3ByPopSubDep2Idx( CFLibDbKeyHash256 PopSubDep2Id )
	{
		final String S_ProcName = "readCachedPopSubDep3ByPopSubDep2Idx";
		ICFBamPopSubDep3ByPopSubDep2IdxKey key = schema.getCFBamBackingStore().getFactoryPopSubDep3().newByPopSubDep2IdxKey();
		key.setRequiredPopSubDep2Id( PopSubDep2Id );
		ArrayList<ICFBamPopSubDep3Obj> arrayList = new ArrayList<ICFBamPopSubDep3Obj>();
		if( indexByPopSubDep2Idx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> dict;
			if( indexByPopSubDep2Idx.containsKey( key ) ) {
				dict = indexByPopSubDep2Idx.get( key );
				int len = dict.size();
				ICFBamPopSubDep3Obj arr[] = new ICFBamPopSubDep3Obj[len];
				Iterator<ICFBamPopSubDep3Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamPopSubDep3Obj obj;
			Iterator<ICFBamPopSubDep3Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopSubDep3Obj> cmp = new Comparator<ICFBamPopSubDep3Obj>() {
			@Override
			public int compare( ICFBamPopSubDep3Obj lhs, ICFBamPopSubDep3Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamPopSubDep3Obj readCachedPopSubDep3ByUNameIdx( CFLibDbKeyHash256 PopSubDep2Id,
		String Name )
	{
		ICFBamPopSubDep3Obj obj = null;
		ICFBamPopSubDep3ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryPopSubDep3().newByUNameIdxKey();
		key.setRequiredPopSubDep2Id( PopSubDep2Id );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamPopSubDep3Obj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamPopSubDep3Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public void deepDisposePopSubDep3ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamPopSubDep3Obj obj = readCachedPopSubDep3ByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposePopSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposePopSubDep3ByTenantIdx";
		ICFBamPopSubDep3Obj obj;
		List<ICFBamPopSubDep3Obj> arrayList = readCachedPopSubDep3ByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamPopSubDep3Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopSubDep3ByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposePopSubDep3ByRelationIdx";
		ICFBamPopSubDep3Obj obj;
		List<ICFBamPopSubDep3Obj> arrayList = readCachedPopSubDep3ByRelationIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamPopSubDep3Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposePopSubDep3ByDefSchemaIdx";
		ICFBamPopSubDep3Obj obj;
		List<ICFBamPopSubDep3Obj> arrayList = readCachedPopSubDep3ByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamPopSubDep3Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopSubDep3ByPopSubDep2Idx( CFLibDbKeyHash256 PopSubDep2Id )
	{
		final String S_ProcName = "deepDisposePopSubDep3ByPopSubDep2Idx";
		ICFBamPopSubDep3Obj obj;
		List<ICFBamPopSubDep3Obj> arrayList = readCachedPopSubDep3ByPopSubDep2Idx( PopSubDep2Id );
		if( arrayList != null )  {
			Iterator<ICFBamPopSubDep3Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopSubDep3ByUNameIdx( CFLibDbKeyHash256 PopSubDep2Id,
		String Name )
	{
		ICFBamPopSubDep3Obj obj = readCachedPopSubDep3ByUNameIdx( PopSubDep2Id,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamPopSubDep3Obj updatePopSubDep3( ICFBamPopSubDep3Obj Obj ) {
		ICFBamPopSubDep3Obj obj = Obj;
		schema.getCFBamBackingStore().getTablePopSubDep3().updatePopSubDep3( null,
			Obj.getPopSubDep3Rec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getPopSubDep3TableObj().getClassCode() ) {
			obj = (ICFBamPopSubDep3Obj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deletePopSubDep3( ICFBamPopSubDep3Obj Obj ) {
		ICFBamPopSubDep3Obj obj = Obj;
		schema.getCFBamBackingStore().getTablePopSubDep3().deletePopSubDep3( null,
			obj.getPopSubDep3Rec() );
		Obj.forget();
	}

	@Override
	public void deletePopSubDep3ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamPopSubDep3Obj obj = readPopSubDep3(Id);
		if( obj != null ) {
			ICFBamPopSubDep3EditObj editObj = (ICFBamPopSubDep3EditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamPopSubDep3EditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposePopSubDep3ByIdIdx( Id );
	}

	@Override
	public void deletePopSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep3Obj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTablePopSubDep3().deletePopSubDep3ByTenantIdx( null,
				TenantId );
			Iterator<ICFBamPopSubDep3Obj> iter = dict.values().iterator();
			ICFBamPopSubDep3Obj obj;
			List<ICFBamPopSubDep3Obj> toForget = new LinkedList<ICFBamPopSubDep3Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopSubDep3().deletePopSubDep3ByTenantIdx( null,
				TenantId );
		}
		deepDisposePopSubDep3ByTenantIdx( TenantId );
	}

	@Override
	public void deletePopSubDep3ByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamPopDepByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByRelationIdx == null ) {
			indexByRelationIdx = new HashMap< ICFBamPopDepByRelationIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep3Obj > >();
		}
		if( indexByRelationIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> dict = indexByRelationIdx.get( key );
			schema.getCFBamBackingStore().getTablePopSubDep3().deletePopSubDep3ByRelationIdx( null,
				RelationId );
			Iterator<ICFBamPopSubDep3Obj> iter = dict.values().iterator();
			ICFBamPopSubDep3Obj obj;
			List<ICFBamPopSubDep3Obj> toForget = new LinkedList<ICFBamPopSubDep3Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByRelationIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopSubDep3().deletePopSubDep3ByRelationIdx( null,
				RelationId );
		}
		deepDisposePopSubDep3ByRelationIdx( RelationId );
	}

	@Override
	public void deletePopSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamPopDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamPopDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep3Obj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTablePopSubDep3().deletePopSubDep3ByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamPopSubDep3Obj> iter = dict.values().iterator();
			ICFBamPopSubDep3Obj obj;
			List<ICFBamPopSubDep3Obj> toForget = new LinkedList<ICFBamPopSubDep3Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopSubDep3().deletePopSubDep3ByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposePopSubDep3ByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deletePopSubDep3ByPopSubDep2Idx( CFLibDbKeyHash256 PopSubDep2Id )
	{
		ICFBamPopSubDep3ByPopSubDep2IdxKey key = schema.getCFBamBackingStore().getFactoryPopSubDep3().newByPopSubDep2IdxKey();
		key.setRequiredPopSubDep2Id( PopSubDep2Id );
		if( indexByPopSubDep2Idx == null ) {
			indexByPopSubDep2Idx = new HashMap< ICFBamPopSubDep3ByPopSubDep2IdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep3Obj > >();
		}
		if( indexByPopSubDep2Idx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep3Obj> dict = indexByPopSubDep2Idx.get( key );
			schema.getCFBamBackingStore().getTablePopSubDep3().deletePopSubDep3ByPopSubDep2Idx( null,
				PopSubDep2Id );
			Iterator<ICFBamPopSubDep3Obj> iter = dict.values().iterator();
			ICFBamPopSubDep3Obj obj;
			List<ICFBamPopSubDep3Obj> toForget = new LinkedList<ICFBamPopSubDep3Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByPopSubDep2Idx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopSubDep3().deletePopSubDep3ByPopSubDep2Idx( null,
				PopSubDep2Id );
		}
		deepDisposePopSubDep3ByPopSubDep2Idx( PopSubDep2Id );
	}

	@Override
	public void deletePopSubDep3ByUNameIdx( CFLibDbKeyHash256 PopSubDep2Id,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamPopSubDep3ByUNameIdxKey,
				ICFBamPopSubDep3Obj >();
		}
		ICFBamPopSubDep3ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryPopSubDep3().newByUNameIdxKey();
		key.setRequiredPopSubDep2Id( PopSubDep2Id );
		key.setRequiredName( Name );
		ICFBamPopSubDep3Obj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTablePopSubDep3().deletePopSubDep3ByUNameIdx( null,
				PopSubDep2Id,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTablePopSubDep3().deletePopSubDep3ByUNameIdx( null,
				PopSubDep2Id,
				Name );
		}
		deepDisposePopSubDep3ByUNameIdx( PopSubDep2Id,
				Name );
	}
}