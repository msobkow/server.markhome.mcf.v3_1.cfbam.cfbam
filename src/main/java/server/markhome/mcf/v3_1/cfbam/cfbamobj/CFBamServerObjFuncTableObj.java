// Description: Java 25 Table Object implementation for ServerObjFunc.

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

public class CFBamServerObjFuncTableObj
	implements ICFBamServerObjFuncTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamServerObjFunc.CLASS_CODE;
	protected static final int backingClassCode = ICFBamServerObjFunc.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> members;
	private Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> allServerObjFunc;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > > indexByTenantIdx;
	private Map< ICFBamServerMethodByUNameIdxKey,
		ICFBamServerObjFuncObj > indexByUNameIdx;
	private Map< ICFBamServerMethodByMethTableIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > > indexByMethTableIdx;
	private Map< ICFBamServerMethodByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > > indexByDefSchemaIdx;
	private Map< ICFBamServerObjFuncByRetTblIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > > indexByRetTblIdx;
	public static String TABLE_NAME = "ServerObjFunc";
	public static String TABLE_DBNAME = "srvofunc";

	public CFBamServerObjFuncTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamServerObjFuncObj>();
		allServerObjFunc = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByMethTableIdx = null;
		indexByDefSchemaIdx = null;
		indexByRetTblIdx = null;
	}

	public CFBamServerObjFuncTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamServerObjFuncObj>();
		allServerObjFunc = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByMethTableIdx = null;
		indexByDefSchemaIdx = null;
		indexByRetTblIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamServerObjFuncTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamServerObjFuncTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allServerObjFunc = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByMethTableIdx = null;
		indexByDefSchemaIdx = null;
		indexByRetTblIdx = null;
		List<ICFBamServerObjFuncObj> toForget = new LinkedList<ICFBamServerObjFuncObj>();
		ICFBamServerObjFuncObj cur = null;
		Iterator<ICFBamServerObjFuncObj> iter = members.values().iterator();
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
	 *	CFBamServerObjFuncObj.
	 */
	@Override
	public ICFBamServerObjFuncObj newInstance() {
		ICFBamServerObjFuncObj inst = new CFBamServerObjFuncObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamServerObjFuncObj.
	 */
	@Override
	public ICFBamServerObjFuncEditObj newEditInstance( ICFBamServerObjFuncObj orig ) {
		ICFBamServerObjFuncEditObj edit = new CFBamServerObjFuncEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamServerObjFuncObj realiseServerObjFunc( ICFBamServerObjFuncObj Obj ) {
		ICFBamServerObjFuncObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamServerObjFuncObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamServerObjFuncObj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamServerMethodByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByMethTableIdx != null ) {
				ICFBamServerMethodByMethTableIdxKey keyMethTableIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
				keyMethTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > mapMethTableIdx = indexByMethTableIdx.get( keyMethTableIdx );
				if( mapMethTableIdx != null ) {
					indexByMethTableIdx.remove( keyMethTableIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByRetTblIdx != null ) {
				ICFBamServerObjFuncByRetTblIdxKey keyRetTblIdx =
					schema.getCFBamBackingStore().getFactoryServerObjFunc().newByRetTblIdxKey();
				keyRetTblIdx.setOptionalRetTableId( keepObj.getOptionalRetTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > mapRetTblIdx = indexByRetTblIdx.get( keyRetTblIdx );
				if( mapRetTblIdx != null ) {
					mapRetTblIdx.remove( keepObj.getPKey() );
					if( mapRetTblIdx.size() <= 0 ) {
						indexByRetTblIdx.remove( keyRetTblIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamServerObjFuncObj)schema.getServerMethodTableObj().realiseServerMethod( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamServerMethodByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByMethTableIdx != null ) {
				ICFBamServerMethodByMethTableIdxKey keyMethTableIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
				keyMethTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > mapMethTableIdx = indexByMethTableIdx.get( keyMethTableIdx );
				if( mapMethTableIdx != null ) {
					mapMethTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRetTblIdx != null ) {
				ICFBamServerObjFuncByRetTblIdxKey keyRetTblIdx =
					schema.getCFBamBackingStore().getFactoryServerObjFunc().newByRetTblIdxKey();
				keyRetTblIdx.setOptionalRetTableId( keepObj.getOptionalRetTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > mapRetTblIdx = indexByRetTblIdx.get( keyRetTblIdx );
				if( mapRetTblIdx != null ) {
					mapRetTblIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allServerObjFunc != null ) {
				allServerObjFunc.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamServerObjFuncObj)schema.getServerMethodTableObj().realiseServerMethod( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allServerObjFunc != null ) {
				allServerObjFunc.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamServerMethodByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByMethTableIdx != null ) {
				ICFBamServerMethodByMethTableIdxKey keyMethTableIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
				keyMethTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > mapMethTableIdx = indexByMethTableIdx.get( keyMethTableIdx );
				if( mapMethTableIdx != null ) {
					mapMethTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRetTblIdx != null ) {
				ICFBamServerObjFuncByRetTblIdxKey keyRetTblIdx =
					schema.getCFBamBackingStore().getFactoryServerObjFunc().newByRetTblIdxKey();
				keyRetTblIdx.setOptionalRetTableId( keepObj.getOptionalRetTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj > mapRetTblIdx = indexByRetTblIdx.get( keyRetTblIdx );
				if( mapRetTblIdx != null ) {
					mapRetTblIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamServerObjFuncObj createServerObjFunc( ICFBamServerObjFuncObj Obj ) {
		ICFBamServerObjFuncObj obj = Obj;
		ICFBamServerObjFunc rec = obj.getServerObjFuncRec();
		schema.getCFBamBackingStore().getTableServerObjFunc().createServerObjFunc(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamServerObjFuncObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamServerObjFuncObj readServerObjFunc( CFLibDbKeyHash256 pkey ) {
		return( readServerObjFunc( pkey, false ) );
	}

	@Override
	public ICFBamServerObjFuncObj readServerObjFunc( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamServerObjFuncObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamServerObjFunc readRec = schema.getCFBamBackingStore().getTableServerObjFunc().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamServerObjFuncObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamServerObjFuncObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamServerObjFuncObj readCachedServerObjFunc( CFLibDbKeyHash256 pkey ) {
		ICFBamServerObjFuncObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeServerObjFunc( ICFBamServerObjFuncObj obj )
	{
		final String S_ProcName = "CFBamServerObjFuncTableObj.reallyDeepDisposeServerObjFunc() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamServerObjFuncObj existing = readCachedServerObjFunc( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamServerObjFuncByRetTblIdxKey keyRetTblIdx = schema.getCFBamBackingStore().getFactoryServerObjFunc().newByRetTblIdxKey();
		keyRetTblIdx.setOptionalRetTableId( existing.getOptionalRetTableId() );


		schema.getParamTableObj().deepDisposeParamByServerMethodIdx( existing.getRequiredId() );

		if( indexByRetTblIdx != null ) {
			if( indexByRetTblIdx.containsKey( keyRetTblIdx ) ) {
				indexByRetTblIdx.get( keyRetTblIdx ).remove( pkey );
				if( indexByRetTblIdx.get( keyRetTblIdx ).size() <= 0 ) {
					indexByRetTblIdx.remove( keyRetTblIdx );
				}
			}
		}


		schema.getServerMethodTableObj().reallyDeepDisposeServerMethod( obj );
	}
	@Override
	public void deepDisposeServerObjFunc( CFLibDbKeyHash256 pkey ) {
		ICFBamServerObjFuncObj obj = readCachedServerObjFunc( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamServerObjFuncObj lockServerObjFunc( CFLibDbKeyHash256 pkey ) {
		ICFBamServerObjFuncObj locked = null;
		ICFBamServerObjFunc lockRec = schema.getCFBamBackingStore().getTableServerObjFunc().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamServerObjFuncObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamServerObjFuncObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockServerObjFunc", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamServerObjFuncObj> readAllServerObjFunc() {
		return( readAllServerObjFunc( false ) );
	}

	@Override
	public List<ICFBamServerObjFuncObj> readAllServerObjFunc( boolean forceRead ) {
		final String S_ProcName = "readAllServerObjFunc";
		if( ( allServerObjFunc == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> map = new HashMap<CFLibDbKeyHash256,ICFBamServerObjFuncObj>();
			allServerObjFunc = map;
			ICFBamServerObjFunc[] recList = schema.getCFBamBackingStore().getTableServerObjFunc().readAllDerived( null );
			ICFBamServerObjFunc rec;
			ICFBamServerObjFuncObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerObjFuncObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerObjFuncObj realised = (ICFBamServerObjFuncObj)obj.realise();
			}
		}
		int len = allServerObjFunc.size();
		ICFBamServerObjFuncObj arr[] = new ICFBamServerObjFuncObj[len];
		Iterator<ICFBamServerObjFuncObj> valIter = allServerObjFunc.values().iterator();
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
		ArrayList<ICFBamServerObjFuncObj> arrayList = new ArrayList<ICFBamServerObjFuncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerObjFuncObj> cmp = new Comparator<ICFBamServerObjFuncObj>() {
			@Override
			public int compare( ICFBamServerObjFuncObj lhs, ICFBamServerObjFuncObj rhs ) {
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
		List<ICFBamServerObjFuncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamServerObjFuncObj> readCachedAllServerObjFunc() {
		final String S_ProcName = "readCachedAllServerObjFunc";
		ArrayList<ICFBamServerObjFuncObj> arrayList = new ArrayList<ICFBamServerObjFuncObj>();
		if( allServerObjFunc != null ) {
			int len = allServerObjFunc.size();
			ICFBamServerObjFuncObj arr[] = new ICFBamServerObjFuncObj[len];
			Iterator<ICFBamServerObjFuncObj> valIter = allServerObjFunc.values().iterator();
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
		Comparator<ICFBamServerObjFuncObj> cmp = new Comparator<ICFBamServerObjFuncObj>() {
			public int compare( ICFBamServerObjFuncObj lhs, ICFBamServerObjFuncObj rhs ) {
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
	public ICFBamServerObjFuncObj readServerObjFuncByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readServerObjFuncByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamServerObjFuncObj readServerObjFuncByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamServerObjFuncObj obj = readServerObjFunc( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamServerObjFuncObj> readServerObjFuncByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readServerObjFuncByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamServerObjFuncObj> readServerObjFuncByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerObjFuncByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerObjFuncObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerObjFuncObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerObjFuncObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerObjFuncObj realised = (ICFBamServerObjFuncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerObjFuncObj arr[] = new ICFBamServerObjFuncObj[len];
		Iterator<ICFBamServerObjFuncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerObjFuncObj> arrayList = new ArrayList<ICFBamServerObjFuncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerObjFuncObj> cmp = new Comparator<ICFBamServerObjFuncObj>() {
			@Override
			public int compare( ICFBamServerObjFuncObj lhs, ICFBamServerObjFuncObj rhs ) {
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
		List<ICFBamServerObjFuncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamServerObjFuncObj readServerObjFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		return( readServerObjFuncByUNameIdx( TableId,
			Name,
			false ) );
	}

	@Override
	public ICFBamServerObjFuncObj readServerObjFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamServerMethodByUNameIdxKey,
				ICFBamServerObjFuncObj >();
		}
		ICFBamServerMethodByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamServerObjFuncObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamServerMethod rec = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByUNameIdx( null,
				TableId,
				Name );
			if( rec != null ) {
				obj = (ICFBamServerObjFuncObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamServerObjFuncObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamServerObjFuncObj> readServerObjFuncByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		return( readServerObjFuncByMethTableIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamServerObjFuncObj> readServerObjFuncByMethTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerObjFuncByMethTableIdx";
		ICFBamServerMethodByMethTableIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		key.setRequiredTableId( TableId );
		Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> dict;
		if( indexByMethTableIdx == null ) {
			indexByMethTableIdx = new HashMap< ICFBamServerMethodByMethTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerObjFuncObj > >();
		}
		if( ( ! forceRead ) && indexByMethTableIdx.containsKey( key ) ) {
			dict = indexByMethTableIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerObjFuncObj>();
			ICFBamServerMethodObj obj;
			ICFBamServerMethod[] recList = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByMethTableIdx( null,
				TableId );
			ICFBamServerMethod rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerObjFuncObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerObjFuncObj realised = (ICFBamServerObjFuncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByMethTableIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerObjFuncObj arr[] = new ICFBamServerObjFuncObj[len];
		Iterator<ICFBamServerObjFuncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerObjFuncObj> arrayList = new ArrayList<ICFBamServerObjFuncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerObjFuncObj> cmp = new Comparator<ICFBamServerObjFuncObj>() {
			@Override
			public int compare( ICFBamServerObjFuncObj lhs, ICFBamServerObjFuncObj rhs ) {
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
		List<ICFBamServerObjFuncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamServerObjFuncObj> readServerObjFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readServerObjFuncByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamServerObjFuncObj> readServerObjFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerObjFuncByDefSchemaIdx";
		ICFBamServerMethodByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamServerMethodByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerObjFuncObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerObjFuncObj>();
			ICFBamServerMethodObj obj;
			ICFBamServerMethod[] recList = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamServerMethod rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerObjFuncObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerObjFuncObj realised = (ICFBamServerObjFuncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerObjFuncObj arr[] = new ICFBamServerObjFuncObj[len];
		Iterator<ICFBamServerObjFuncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerObjFuncObj> arrayList = new ArrayList<ICFBamServerObjFuncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerObjFuncObj> cmp = new Comparator<ICFBamServerObjFuncObj>() {
			@Override
			public int compare( ICFBamServerObjFuncObj lhs, ICFBamServerObjFuncObj rhs ) {
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
		List<ICFBamServerObjFuncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamServerObjFuncObj> readServerObjFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId )
	{
		return( readServerObjFuncByRetTblIdx( RetTableId,
			false ) );
	}

	@Override
	public List<ICFBamServerObjFuncObj> readServerObjFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerObjFuncByRetTblIdx";
		ICFBamServerObjFuncByRetTblIdxKey key = schema.getCFBamBackingStore().getFactoryServerObjFunc().newByRetTblIdxKey();
		key.setOptionalRetTableId( RetTableId );
		Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> dict;
		if( indexByRetTblIdx == null ) {
			indexByRetTblIdx = new HashMap< ICFBamServerObjFuncByRetTblIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerObjFuncObj > >();
		}
		if( ( ! forceRead ) && indexByRetTblIdx.containsKey( key ) ) {
			dict = indexByRetTblIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerObjFuncObj>();
			ICFBamServerObjFuncObj obj;
			ICFBamServerObjFunc[] recList = schema.getCFBamBackingStore().getTableServerObjFunc().readDerivedByRetTblIdx( null,
				RetTableId );
			ICFBamServerObjFunc rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerObjFuncObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerObjFuncObj realised = (ICFBamServerObjFuncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRetTblIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerObjFuncObj arr[] = new ICFBamServerObjFuncObj[len];
		Iterator<ICFBamServerObjFuncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerObjFuncObj> arrayList = new ArrayList<ICFBamServerObjFuncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerObjFuncObj> cmp = new Comparator<ICFBamServerObjFuncObj>() {
			@Override
			public int compare( ICFBamServerObjFuncObj lhs, ICFBamServerObjFuncObj rhs ) {
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
		List<ICFBamServerObjFuncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamServerObjFuncObj readCachedServerObjFuncByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamServerObjFuncObj obj = null;
		obj = readCachedServerObjFunc( Id );
		return( obj );
	}

	@Override
	public List<ICFBamServerObjFuncObj> readCachedServerObjFuncByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedServerObjFuncByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamServerObjFuncObj> arrayList = new ArrayList<ICFBamServerObjFuncObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamServerObjFuncObj arr[] = new ICFBamServerObjFuncObj[len];
				Iterator<ICFBamServerObjFuncObj> valIter = dict.values().iterator();
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
			ICFBamServerObjFuncObj obj;
			Iterator<ICFBamServerObjFuncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerObjFuncObj> cmp = new Comparator<ICFBamServerObjFuncObj>() {
			@Override
			public int compare( ICFBamServerObjFuncObj lhs, ICFBamServerObjFuncObj rhs ) {
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
	public ICFBamServerObjFuncObj readCachedServerObjFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamServerObjFuncObj obj = null;
		ICFBamServerMethodByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamServerObjFuncObj> valIter = members.values().iterator();
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
			Iterator<ICFBamServerObjFuncObj> valIter = members.values().iterator();
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
	public List<ICFBamServerObjFuncObj> readCachedServerObjFuncByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedServerObjFuncByMethTableIdx";
		ICFBamServerMethodByMethTableIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamServerObjFuncObj> arrayList = new ArrayList<ICFBamServerObjFuncObj>();
		if( indexByMethTableIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> dict;
			if( indexByMethTableIdx.containsKey( key ) ) {
				dict = indexByMethTableIdx.get( key );
				int len = dict.size();
				ICFBamServerObjFuncObj arr[] = new ICFBamServerObjFuncObj[len];
				Iterator<ICFBamServerObjFuncObj> valIter = dict.values().iterator();
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
			ICFBamServerObjFuncObj obj;
			Iterator<ICFBamServerObjFuncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerObjFuncObj> cmp = new Comparator<ICFBamServerObjFuncObj>() {
			@Override
			public int compare( ICFBamServerObjFuncObj lhs, ICFBamServerObjFuncObj rhs ) {
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
	public List<ICFBamServerObjFuncObj> readCachedServerObjFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedServerObjFuncByDefSchemaIdx";
		ICFBamServerMethodByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamServerObjFuncObj> arrayList = new ArrayList<ICFBamServerObjFuncObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamServerObjFuncObj arr[] = new ICFBamServerObjFuncObj[len];
				Iterator<ICFBamServerObjFuncObj> valIter = dict.values().iterator();
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
			ICFBamServerObjFuncObj obj;
			Iterator<ICFBamServerObjFuncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerObjFuncObj> cmp = new Comparator<ICFBamServerObjFuncObj>() {
			@Override
			public int compare( ICFBamServerObjFuncObj lhs, ICFBamServerObjFuncObj rhs ) {
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
	public List<ICFBamServerObjFuncObj> readCachedServerObjFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId )
	{
		final String S_ProcName = "readCachedServerObjFuncByRetTblIdx";
		ICFBamServerObjFuncByRetTblIdxKey key = schema.getCFBamBackingStore().getFactoryServerObjFunc().newByRetTblIdxKey();
		key.setOptionalRetTableId( RetTableId );
		ArrayList<ICFBamServerObjFuncObj> arrayList = new ArrayList<ICFBamServerObjFuncObj>();
		if( indexByRetTblIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> dict;
			if( indexByRetTblIdx.containsKey( key ) ) {
				dict = indexByRetTblIdx.get( key );
				int len = dict.size();
				ICFBamServerObjFuncObj arr[] = new ICFBamServerObjFuncObj[len];
				Iterator<ICFBamServerObjFuncObj> valIter = dict.values().iterator();
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
			ICFBamServerObjFuncObj obj;
			Iterator<ICFBamServerObjFuncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerObjFuncObj> cmp = new Comparator<ICFBamServerObjFuncObj>() {
			@Override
			public int compare( ICFBamServerObjFuncObj lhs, ICFBamServerObjFuncObj rhs ) {
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
	public void deepDisposeServerObjFuncByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamServerObjFuncObj obj = readCachedServerObjFuncByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeServerObjFuncByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeServerObjFuncByTenantIdx";
		ICFBamServerObjFuncObj obj;
		List<ICFBamServerObjFuncObj> arrayList = readCachedServerObjFuncByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamServerObjFuncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeServerObjFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamServerObjFuncObj obj = readCachedServerObjFuncByUNameIdx( TableId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeServerObjFuncByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeServerObjFuncByMethTableIdx";
		ICFBamServerObjFuncObj obj;
		List<ICFBamServerObjFuncObj> arrayList = readCachedServerObjFuncByMethTableIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamServerObjFuncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeServerObjFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeServerObjFuncByDefSchemaIdx";
		ICFBamServerObjFuncObj obj;
		List<ICFBamServerObjFuncObj> arrayList = readCachedServerObjFuncByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamServerObjFuncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeServerObjFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId )
	{
		final String S_ProcName = "deepDisposeServerObjFuncByRetTblIdx";
		ICFBamServerObjFuncObj obj;
		List<ICFBamServerObjFuncObj> arrayList = readCachedServerObjFuncByRetTblIdx( RetTableId );
		if( arrayList != null )  {
			Iterator<ICFBamServerObjFuncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamServerObjFuncObj updateServerObjFunc( ICFBamServerObjFuncObj Obj ) {
		ICFBamServerObjFuncObj obj = Obj;
		schema.getCFBamBackingStore().getTableServerObjFunc().updateServerObjFunc( null,
			Obj.getServerObjFuncRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getServerObjFuncTableObj().getClassCode() ) {
			obj = (ICFBamServerObjFuncObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteServerObjFunc( ICFBamServerObjFuncObj Obj ) {
		ICFBamServerObjFuncObj obj = Obj;
		schema.getCFBamBackingStore().getTableServerObjFunc().deleteServerObjFunc( null,
			obj.getServerObjFuncRec() );
		Obj.forget();
	}

	@Override
	public void deleteServerObjFuncByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamServerObjFuncObj obj = readServerObjFunc(Id);
		if( obj != null ) {
			ICFBamServerObjFuncEditObj editObj = (ICFBamServerObjFuncEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamServerObjFuncEditObj)obj.beginEdit();
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
		deepDisposeServerObjFuncByIdIdx( Id );
	}

	@Override
	public void deleteServerObjFuncByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerObjFuncObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableServerObjFunc().deleteServerObjFuncByTenantIdx( null,
				TenantId );
			Iterator<ICFBamServerObjFuncObj> iter = dict.values().iterator();
			ICFBamServerObjFuncObj obj;
			List<ICFBamServerObjFuncObj> toForget = new LinkedList<ICFBamServerObjFuncObj>();
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
			schema.getCFBamBackingStore().getTableServerObjFunc().deleteServerObjFuncByTenantIdx( null,
				TenantId );
		}
		deepDisposeServerObjFuncByTenantIdx( TenantId );
	}

	@Override
	public void deleteServerObjFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamServerMethodByUNameIdxKey,
				ICFBamServerObjFuncObj >();
		}
		ICFBamServerMethodByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamServerObjFuncObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableServerObjFunc().deleteServerObjFuncByUNameIdx( null,
				TableId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableServerObjFunc().deleteServerObjFuncByUNameIdx( null,
				TableId,
				Name );
		}
		deepDisposeServerObjFuncByUNameIdx( TableId,
				Name );
	}

	@Override
	public void deleteServerObjFuncByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		ICFBamServerMethodByMethTableIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByMethTableIdx == null ) {
			indexByMethTableIdx = new HashMap< ICFBamServerMethodByMethTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerObjFuncObj > >();
		}
		if( indexByMethTableIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> dict = indexByMethTableIdx.get( key );
			schema.getCFBamBackingStore().getTableServerObjFunc().deleteServerObjFuncByMethTableIdx( null,
				TableId );
			Iterator<ICFBamServerObjFuncObj> iter = dict.values().iterator();
			ICFBamServerObjFuncObj obj;
			List<ICFBamServerObjFuncObj> toForget = new LinkedList<ICFBamServerObjFuncObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByMethTableIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableServerObjFunc().deleteServerObjFuncByMethTableIdx( null,
				TableId );
		}
		deepDisposeServerObjFuncByMethTableIdx( TableId );
	}

	@Override
	public void deleteServerObjFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamServerMethodByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamServerMethodByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerObjFuncObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableServerObjFunc().deleteServerObjFuncByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamServerObjFuncObj> iter = dict.values().iterator();
			ICFBamServerObjFuncObj obj;
			List<ICFBamServerObjFuncObj> toForget = new LinkedList<ICFBamServerObjFuncObj>();
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
			schema.getCFBamBackingStore().getTableServerObjFunc().deleteServerObjFuncByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeServerObjFuncByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteServerObjFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId )
	{
		ICFBamServerObjFuncByRetTblIdxKey key = schema.getCFBamBackingStore().getFactoryServerObjFunc().newByRetTblIdxKey();
		key.setOptionalRetTableId( RetTableId );
		if( indexByRetTblIdx == null ) {
			indexByRetTblIdx = new HashMap< ICFBamServerObjFuncByRetTblIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerObjFuncObj > >();
		}
		if( indexByRetTblIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerObjFuncObj> dict = indexByRetTblIdx.get( key );
			schema.getCFBamBackingStore().getTableServerObjFunc().deleteServerObjFuncByRetTblIdx( null,
				RetTableId );
			Iterator<ICFBamServerObjFuncObj> iter = dict.values().iterator();
			ICFBamServerObjFuncObj obj;
			List<ICFBamServerObjFuncObj> toForget = new LinkedList<ICFBamServerObjFuncObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByRetTblIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableServerObjFunc().deleteServerObjFuncByRetTblIdx( null,
				RetTableId );
		}
		deepDisposeServerObjFuncByRetTblIdx( RetTableId );
	}
}